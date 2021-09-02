package com.example.demo.services;

import com.example.demo.models.AutoShop;
import com.example.demo.models.LAMUser;
import com.example.demo.models.Review;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.services.base.BaseServiceImpl;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.demo.utils.Constants.DEFAULT_PAGE;
import static com.example.demo.utils.Constants.DEFAULT_PAGE_SIZE;
import static java.lang.String.format;

@Service
@GraphQLApi
public class ReviewService extends BaseServiceImpl<Review, UUID, ReviewRepository> {

    private static final String DUPLICATE_REVIEW_EXCEPTION_MESSAGE = "%s has already submitted a review for this shop.";

    private final AutoShopService shopService;
    private final LAMUserService  userService;

    @Autowired
    public ReviewService(ReviewRepository repository, AutoShopService shopService, LAMUserService userService) {
        super(repository);
        this.shopService = shopService;
        this.userService = userService;
    }

    @GraphQLQuery(name = "shopReviews")
    public Page<Review> getShopReviews(
            @GraphQLArgument(name = "page", defaultValue = DEFAULT_PAGE) int page,
            @GraphQLArgument(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
            @GraphQLContext AutoShop shop
    ) {
        return repository.findByShopId(shop.getId(), PageRequest.of(page, size));
    }

    @GraphQLQuery(name = "userReviews")
    public Page<Review> getUserReviews(
            @GraphQLArgument(name = "page", defaultValue = DEFAULT_PAGE) int page,
            @GraphQLArgument(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
            @GraphQLContext LAMUser user
    ) {
        return repository.findByUserId(user.getId(), PageRequest.of(page, size));
    }

    @GraphQLQuery(name = "ratingsCount")
    public int getRatingsCount(
            @GraphQLContext AutoShop shop
    ) {
        return repository.countByShopId(shop.getId());
    }

    @GraphQLMutation(name = "saveReview")
    public Review saveReview(
            @GraphQLNonNull Review review,
            @GraphQLNonNull UUID userId,
            @GraphQLNonNull UUID shopId
    ) {
        if (checkForExistingUserReview(userId)) {
            throw new IllegalArgumentException(format(DUPLICATE_REVIEW_EXCEPTION_MESSAGE, userId));
        }
        LAMUser  user       = userService.findById(userId);
        short    userRating = review.getUserRating();
        AutoShop shop       = shopService.findById(shopId);
        short    shopRating = shop.getRating();

        // In case of first review
        if (shopRating == 0) {
            shopRating = userRating;
        }
        if (userRating != 0) {
            shop.setRating((short) ((userRating + shopRating) / 2));
        }

        review.setUser(user);
        review.setShop(shop);
        return super.create(review);
    }

    @GraphQLMutation(name = "updateReview")
    public Review update(
            @GraphQLNonNull UUID reviewId,
            @GraphQLNonNull UUID userId,
            @GraphQLNonNull UUID shopId,
            @GraphQLNonNull short userRating,
            @GraphQLArgument(name = "reviewComment") String comment
    ) {
        Review review = new Review(userId, shopId);
        review.setComment(comment);
        review.setUserRating(userRating);
        return super.update(reviewId, review);
    }

    @Override
    @GraphQLMutation(name = "deleteReview")
    public boolean delete(@GraphQLNonNull UUID uuid) {
        return super.delete(uuid);
    }

    public boolean checkForExistingUserReview(UUID userId) {
        return repository.existsByUserId(userId);
    }
}
