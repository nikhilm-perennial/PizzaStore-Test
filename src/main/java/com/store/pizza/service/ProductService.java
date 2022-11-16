package com.store.pizza.service;

import com.store.pizza.enums.Category;
import com.store.pizza.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> getAllProducts();

    Product getProductById(long id);

    Product getPizzaById(long id);

    Product getProductByName(String name);

    List<Product> getProductByCategory(Category category);

    List<Product> getAllSimpleProducts();

    List<Product> getAllComboProduct();

    List<Product> getAllPizza();

    void deleteProduct(long id);
}
