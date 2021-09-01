package com.example.demo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "visits")
public class Visit extends BaseEntity {
    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_visits"),
            nullable = false
    )
    @NotNull
    private LAMUser user;

    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_shop_visits"),
            nullable = false
    )
    @NotNull
    private AutoShop shop;

    private int visits;
}
