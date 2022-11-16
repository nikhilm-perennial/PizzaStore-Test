package com.store.pizza.repository;

import com.store.pizza.enums.Category;
import com.store.pizza.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    /**
     * Find Product as a simple product using id
     * @param id of simple product to be searched
     * @return simple product available with the given id
     */
    Product findProductBySimpleProduct_SimpleProductId(long id);

    /**
     * Find Product as a pizza using id
     * @param id of pizza to be searched
     * @return pizza available with the given id
     */
    Product findProductByPizza_PizzaId(long id);


    Product findProductByName(String name);

    List<Product> findAllByCategory(Category category);

    @Query("SELECT p from Product p join p.simpleProduct")
    List<Product> findAllSimpleProduct();

    @Query("select p from Product p join p.comboProduct")
    List<Product> findAllComboProduct();

    @Query("SELECT p from Product p join p.pizza")
    List<Product> findAllPizza();



}
