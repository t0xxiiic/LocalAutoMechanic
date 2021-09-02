package com.example.demo.models;

import com.example.demo.enums.PriceRange;
import io.leangen.graphql.annotations.GraphQLIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.demo.utils.Constants.SHOP_UNIQUE_NAME_AND_CITY_CONSTRAINT;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(
        name = "shops",
        uniqueConstraints = {
                @UniqueConstraint(name = SHOP_UNIQUE_NAME_AND_CITY_CONSTRAINT, columnNames = {"name", "city"})
        })
@DynamicInsert
@DynamicUpdate
public class AutoShop extends BaseEntity {

    @GraphQLIgnore private static final int    NAME_MIN_LENGTH            = 3;
    @GraphQLIgnore private static final int    NAME_MAX_LENGTH            = 60;
    @GraphQLIgnore private static final String NAME_VALIDATION_MSG        = "Shop name must be between @min and @max characters long";
    @GraphQLIgnore private static final int    DESCRIPTION_MIN_LENGTH     = 20;
    @GraphQLIgnore private static final int    DESCRIPTION_MAX_LENGTH     = 700;
    @GraphQLIgnore private static final String DESCRIPTION_VALIDATION_MSG = "Description must be between @min and @max characters long";
    @GraphQLIgnore private static final String PHONE_REGEX                = "(\\+359[0-9]{9})|(0[0-9]{9})";
    @GraphQLIgnore private static final int    RATING_MIN_LENGTH          = 0;
    @GraphQLIgnore private static final int    RATING_MAX_LENGTH          = 5;

    public AutoShop(UUID id) {
        setId(id);
    }

    /* TODO Add additional info like work hours, price range, supported makes price range enum: CHEAP, NORMAL, EXPENSIVE */

    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH, message = NAME_VALIDATION_MSG)
    @NotNull private String name;
    @NotNull private Double latitude;
    @NotNull private Double longitude;
    @NotNull private String city;
    @Pattern(regexp = PHONE_REGEX, message = "Phone number must match [+359]00 000 0000 or [0]00 000 0000 pattern")
    @NotNull private String phoneNumber;

    private String     address;
    private String     profilePic;
    @Size(min = DESCRIPTION_MIN_LENGTH, max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_VALIDATION_MSG)
    private String     description;
    @Enumerated(EnumType.STRING)
    private PriceRange priceRange;
    @Min(value = RATING_MIN_LENGTH) @Max(value = RATING_MAX_LENGTH)
    private short      rating;

    @GraphQLIgnore @OneToMany(mappedBy = "shop") @Builder.Default
    private List<WorkSchedule> workSchedule = new ArrayList<>();

    @GraphQLIgnore @OneToMany(mappedBy = "shop") @Builder.Default
    private List<Review> reviews = new ArrayList<>();
}
