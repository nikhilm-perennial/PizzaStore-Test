package com.store.pizza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ComboProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comboId;

    @OneToOne
    @JoinColumn
    private SimpleProduct simpleProduct;

    @OneToOne
    @JoinColumn
    private Pizza pizza;

    @NotNull(message = "Base price should not be 0.0")
    private double basePrice;

    @NotNull(message = "Sale price should not be 0.0")
    private double salePrice;

}
