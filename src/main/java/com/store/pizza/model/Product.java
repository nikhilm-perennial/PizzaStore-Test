package com.store.pizza.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.store.pizza.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name should not be null")
    @NotEmpty
    private String name;

    @NotNull(message = "Price should not be null")
    private double price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "simpleproduct_id",referencedColumnName = "simpleProductId")
    private SimpleProduct simpleProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pizza_id",referencedColumnName = "pizzaId")
    private Pizza pizza;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "combo_id",referencedColumnName = "comboId")
    private ComboProduct comboProduct;

}
