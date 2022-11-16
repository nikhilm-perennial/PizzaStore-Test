package com.store.pizza.service.impl;

import com.store.pizza.enums.Category;
import com.store.pizza.model.Product;
import com.store.pizza.enums.Size;
import com.store.pizza.repository.ProductRepository;
import com.store.pizza.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Save the product in database
     * @param product to be saved
     * @return successfully save the product in database
     */
    @Override
    public Product save(Product product) {
        log.info("Product Received: "+product);

        if(product.getSimpleProduct()==null && product.getPizza()==null){
            Product simpleProduct = productRepository
                    .findProductBySimpleProduct_SimpleProductId(product.getComboProduct()
                            .getSimpleProduct().getSimpleProductId());

            Product pizza = productRepository
                    .findProductByPizza_PizzaId(product.getComboProduct().getPizza().getPizzaId());

            double crustPrice = 0.0;
            for (Map.Entry<String,Double> crustEntry : pizza.getPizza().getAvailableCrust().entrySet()){
                crustPrice = crustEntry.getValue();
            }

            double sizePrice = 0.0;
            for (Map.Entry<Size, Double> sizeEntry : pizza.getPizza().getAvailableSize().entrySet()){
                sizePrice = sizeEntry.getValue();
            }

            double simpleProductPrice = simpleProduct.getPrice();
            double pizzaPrice = crustPrice + sizePrice;

            double basePrice = simpleProductPrice + pizzaPrice;
            double salePrice = basePrice - (basePrice * 0.15);

            product.setPrice(salePrice);
            product.getComboProduct().setBasePrice(basePrice);
            product.getComboProduct().setSalePrice(salePrice);
        }

        return productRepository.save(product);
    }

    @Override //done
    public List<Product> getAllProducts() {
        log.info("Display All Products");
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()){
            return Collections.emptyList();
        }
        return productList;
    }

    @Override //done
    public Product getProductById(long id){
        log.info("Simple_Product_Id Received: "+id);
        Product product = productRepository.findProductBySimpleProduct_SimpleProductId(id);
        if (product == null){
            throw new NullPointerException("No Simple_Product available with given id");
        }
        log.info("SimpleProduct: "+product);
        return product;
    }

    @Override //done
    public Product getPizzaById(long id){
        log.info("Pizza_Id Received: "+id);
        Product product = productRepository.findProductByPizza_PizzaId(id);
        if (product == null){
            throw new NullPointerException("No Pizza Available with given id");
        }
        log.info("Pizza: "+product);
        return product;
    }

    @Override //done
    public Product getProductByName(String name){
        log.info("Product_Name Received: "+name);
        Product product = productRepository.findProductByName(name);
        if (product == null){
            throw new NullPointerException("No Product available with given name");
        }
        log.info("Product: "+product);
        return product;
    }

    @Override //done
    public List<Product> getProductByCategory(Category category){
        log.info("Category Received: "+category);
        List<Product> productList = productRepository.findAllByCategory(category);
        if (productList.isEmpty()){
            return Collections.emptyList();
        }
        return productList;
    }

    @Override //done
    public List<Product> getAllSimpleProducts(){
        log.info("Display All SimpleProducts");
        List<Product> productList = productRepository.findAllSimpleProduct();
        if (productList.isEmpty()){
            return Collections.emptyList();
        }
        return productList;
    }

    @Override //done
    public List<Product> getAllComboProduct(){
        log.info("Display All ComboProducts");
        List<Product> productList = productRepository.findAllComboProduct();
        if (productList.isEmpty()){
            return Collections.emptyList();
        }
        return productList;
    }

    @Override //done
    public List<Product> getAllPizza(){
        log.info("Display All Pizza");
        List<Product> productList = productRepository.findAllPizza();
        if (productList.isEmpty()){
            return Collections.emptyList();
        }
        return productList;
    }

    @Override
    public void deleteProduct(long id){
        log.info("Product_Id received: "+id);
//        Product product = productRepository.getById(id);
//        if (product==null){
//            throw new NullPointerException("No Product available with given id");
//        }
        productRepository.deleteById(id);
//        log.info("Product Deleted: "+product);
    }
}
