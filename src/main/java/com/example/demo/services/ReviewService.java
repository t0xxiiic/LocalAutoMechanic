package com.example.demo.services;

import com.example.demo.models.AutoShop;
import com.example.demo.models.LAMUser;
import com.example.demo.models.Review;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.services.base.BaseServiceImpl;
import com.example.demo.utils.Constants;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.demo.utils.Constants.DEFAULT_PAGE;
import static com.example.demo.utils.Constants.DEFAULT_PAGE_SIZE;

@Service
@GraphQLApi
public class ReviewService extends BaseServiceImpl<Review, UUID, ReviewRepository> {

    private static final String DUPLICATE_REVIEW_EXCEPTION_MESSAGE = "%s has already submitted a review for this shop.";

    private final AutoShopService shopService;
    private final LAMUserService userService;

    @Autowired
    public ReviewService(ReviewRepository repository, AutoShopService shopService, LAMUserService userService) {
        super(repository);
        this.shopService = shopService;
        this.userService = userService;
    }

    @GraphQLQuery(name = "getShopReviewsPaged")
    public Page<Review> getShopReviewsPaged(
            @GraphQLArgument(name = "page", defaultValue = DEFAULT_PAGE) int page,
            @GraphQLArgument(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
            @GraphQLNonNull UUID shopId
    ) {
        return repository.findByShopId(shopId, PageRequest.of(page, size));
    }

    @GraphQLMutation(name = "saveReview")
    public Review saveReview(
            @GraphQLNonNull Review review,
            @GraphQLNonNull UUID userId,
            @GraphQLNonNull UUID shopId
    ) {
        if (checkForExistingUserReview(userId)) {
            throw new IllegalArgumentException(String.format(DUPLICATE_REVIEW_EXCEPTION_MESSAGE, userId));
        }
        LAMUser user = userService.findById(userId);
        AutoShop shop = shopService.findById(shopId);
        shop.setRating((short) ((shop.getRating() + review.getUserRating()) / 2));
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
