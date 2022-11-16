package com.store.pizza.model;

import com.store.pizza.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SimpleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long simpleProductId;

    @Enumerated(EnumType.STRING)
    private Size size;

}
