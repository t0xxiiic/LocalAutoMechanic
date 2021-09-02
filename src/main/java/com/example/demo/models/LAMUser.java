package com.example.demo.models;

import io.leangen.graphql.annotations.GraphQLIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

import static com.example.demo.utils.Constants.USER_UNIQUE_EMAIL_CONSTRAINT;
import static com.example.demo.utils.Constants.USER_UNIQUE_USERNAME_CONSTRAINT;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = USER_UNIQUE_USERNAME_CONSTRAINT, columnNames = {"username"}),
                @UniqueConstraint(name = USER_UNIQUE_EMAIL_CONSTRAINT, columnNames = {"email"})
        })
@DynamicUpdate
@DynamicInsert
public class LAMUser extends BaseEntity {

    public LAMUser(UUID id) {
        setId(id);
    }

    @Size(min = 5, max = 30)
    @NotNull private String username;
    @Email
    @NotNull private String email;

    private String    firstName;
    private String    lastName;
    @Builder.Default
    private String    profilePic = "https://i.imgur.com/km4Popk.png";
    private LocalDate birthDay;
    private String    phoneNumber;

    @GraphQLIgnore
    @ManyToMany
    @JoinTable(
            name = "users_shops",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_users_shops")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "shop_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_shops_shops")
            )
    )
    @Builder.Default
    private Set<AutoShop> shopsOwned = new HashSet<>();

    @GraphQLIgnore
    @ManyToMany
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_users_favorites")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "shop_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_shops_favorites")
            )
    )
    @Builder.Default
    private Set<AutoShop> favorites = new HashSet<>();

    @GraphQLIgnore @OneToMany(mappedBy = "user") @Builder.Default
    private List<Visit> visits = new ArrayList<>();

    @GraphQLIgnore @OneToMany(mappedBy = "user") @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @GraphQLIgnore @OneToMany(mappedBy = "user") @Builder.Default
    private List<Comment> userComments = new ArrayList<>();
}
