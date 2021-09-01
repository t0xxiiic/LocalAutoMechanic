package com.example.demo.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.demo.utils.Constants.REVIEW_UNIQUE_USER_ID_CONSTRAINT;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(
        name = "reviews",
        uniqueConstraints = @UniqueConstraint(name = REVIEW_UNIQUE_USER_ID_CONSTRAINT, columnNames = {"user_id"})
)
public class Review extends BaseEntity {

    public Review(UUID userId, UUID shopId) {
        setUser(new LAMUser(userId));
        setShop(new AutoShop(shopId));
    }

    public Review(UUID reviewId) {
        setId(reviewId);
    }

    // TODO Add additional info like price range

    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_reviews"),
            nullable = false
    )
    private LAMUser user;

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_shop_reviews"),
            nullable = false
    )
    private AutoShop shop;

    @NotNull
    private short userRating;

    private String comment;

    @OneToMany(mappedBy = "review")
    private List<Comment> thread = new ArrayList<>();
}
