package com.store.pizza.controller;

import com.store.pizza.enums.Category;
import com.store.pizza.model.Product;
import com.store.pizza.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class
ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Save Product to PizzaStore
     * @param product product to be stored in database
     * @return successfully store product in store catalog
     */
    @PostMapping("/save/product") //done
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    /**
     * Display All products in Store
     * @return display all available products
     */
    @GetMapping("/product")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/id={id}") //done
    public Product getById(@PathVariable String id){
        Product product = productService.getProductById(Long.parseLong(id));
        System.out.println(product);
        return product;
    }


    /**
     * Search for product using product name
     * @param name Product to be searched using name
     * @return display available product with the given name
     */
    @GetMapping("/product/name/{name}")  //done
    public Product getProductByName(@PathVariable String name){
        Product product = productService.getProductByName(name);
        return product;
    }

    /**
     * Search for products using category
     * @param category product to be searched using category
     * @return display all the available products in that category
     */
    @GetMapping("/product/category/{category}") //done
    public List<Product> getProductByCategory(@PathVariable Category category){
        return productService.getProductByCategory(category);
    }

    /**
     * Display all the simple products
     * @return display all available simple products
     */
    @GetMapping("/product/simple-product") //done
    public List<Product> getAllSimpleProduct(){
        return productService.getAllSimpleProducts();
    }

    /**
     * Display all the combo products
     * @return display all the available combo products
     */
    @GetMapping("/product/combo-product") //done
    public List<Product> getAllComboProduct(){
        return productService.getAllComboProduct();
    }

    /**
     * Display all pizzas
     * @return display all the available pizzas
     */
    @GetMapping("/product/pizza") //done
    public List<Product> getAllPizza(){
        return productService.getAllPizza();
    }

    /**
     * Delete the product from store database
     * @param id of the product to be deleted from database
     */
    @DeleteMapping("/product/delete/id={id}") //done
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(Long.parseLong(id));
    }
}
