package com.example.demo.models;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.demo.utils.Constants.SHOP_UNIQUE_NAME_CONSTRAINT;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "shops", uniqueConstraints = @UniqueConstraint(name = SHOP_UNIQUE_NAME_CONSTRAINT, columnNames = "name"))
@DynamicInsert
@DynamicUpdate
public class AutoShop extends BaseEntity {

    public AutoShop(UUID id) {
        setId(id);
    }

    // TODO Add additional info like work hours, price range, supported makes
    // TODO price range enum: CHEAP, NORMAL, EXPENSIVE

    private String name;
    private String address;
    private String profilePic;
    private String city;
    private String description;
    private String phoneNumber;
    private short  rating;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;

    @OneToMany(mappedBy = "shop")
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
}
