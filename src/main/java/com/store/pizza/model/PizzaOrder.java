package com.store.pizza.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@Entity
@Data
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userPhone;

    private String pizzaName;

    private String crustSize;

    private String crustType;

    @ElementCollection
    private List<String> toppings;



    private double orderAmount;

    private LocalDateTime orderDate;

}
