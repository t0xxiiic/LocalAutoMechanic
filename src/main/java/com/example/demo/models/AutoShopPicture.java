package com.example.demo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "shops_pictures")
public class AutoShopPicture extends BaseEntity {
    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_shop_pictures"),
            nullable = false
    )
    @NotNull
    private AutoShop shop;

    @URL
    private String pictureURL;
}
