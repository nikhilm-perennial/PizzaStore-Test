package com.store.pizza.model;

import com.store.pizza.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pizzaId;

    @ElementCollection
    @MapKeyColumn(name = "size_name")
    @Column(name = "price")
    @CollectionTable(name = "CrustSize")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Size,Double> availableSize;

    @ElementCollection
    @MapKeyColumn(name = "type_name")
    @Column(name = "price")
    @CollectionTable(name = "CrustType")
    private Map<String,Double> availableCrust;

}
