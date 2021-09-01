package com.example.demo.services;

import com.example.demo.models.Comment;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.services.base.BaseServiceImpl;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

import static com.example.demo.utils.Constants.*;

@Service
@GraphQLApi
public class CommentService extends BaseServiceImpl<Comment, UUID, CommentRepository> {
    private final LAMUserService userService;
    private final ReviewService  reviewService;

    @Autowired
    public CommentService(
            CommentRepository repository,
            LAMUserService userService,
            ReviewService reviewService
    ) {
        super(repository);
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GraphQLQuery(name = "getReviewCommentsPaged")
    public List<Comment> getReviewCommentsPaged(
            @GraphQLArgument(name = "page", defaultValue = DEFAULT_PAGE) int page,
            @GraphQLArgument(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
            @GraphQLNonNull UUID reviewId
    ) {
        return super.repository.findByReviewId(reviewId, PageRequest.of(page, size)).getContent();
    }


    @GraphQLMutation(name = "addReviewComment")
    public Comment addCommentToReview(
            @GraphQLNonNull UUID reviewId,
            @GraphQLNonNull UUID userId,
            @GraphQLNonNull @NotBlank String commentText
    ) {
        if (!userService.existsById(userId)) {
            throw new IllegalArgumentException(String.format(ENTITY_NOT_FOUND_MSG, userId));
        }
        if (!reviewService.existsById(reviewId)) {
            throw new IllegalArgumentException(String.format(ENTITY_NOT_FOUND_MSG, reviewId));
        }
        Comment comment = new Comment(userId, reviewId);
        comment.setCommentText(commentText);
        return super.create(comment);
    }

    @GraphQLMutation(name = "updateComment")
    public Comment update(@GraphQLNonNull UUID commentId, @GraphQLNonNull String commentText) {
        Comment comment = super.findById(commentId);
        comment.setCommentText(commentText);
        return super.update(commentId, comment);
    }

    @Override
    @GraphQLMutation(name = "deleteComment")
    public boolean delete(@GraphQLNonNull UUID uuid) {
        return super.delete(uuid);
    }
}
