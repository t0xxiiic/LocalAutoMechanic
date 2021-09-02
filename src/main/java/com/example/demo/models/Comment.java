package com.example.demo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "comments")
@DynamicUpdate
public class Comment extends BaseEntity {

    public Comment(UUID userId, UUID reviewId) {
        setUser(new LAMUser(userId));
        setReview(new Review(userId, reviewId));
    }

    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_comments"),
            nullable = false
    )
    @NotNull
    private LAMUser user;

    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_review_comments"),
            nullable = false
    )
    @NotNull
    private Review review;

    @NotBlank
    private String commentText;
}
