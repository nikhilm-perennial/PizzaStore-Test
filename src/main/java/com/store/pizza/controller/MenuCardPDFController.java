package com.store.pizza.controller;

import com.lowagie.text.DocumentException;
import com.store.pizza.model.Product;
import com.store.pizza.pdfutil.MenuCardGenerator;
import com.store.pizza.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MenuCardPDFController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/menu-card")
    public void exportPDF(HttpServletResponse res) throws DocumentException, IOException{

        res.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDate + ".pdf";
        res.setHeader(headerkey,headerValue);

        List<Product> productList = productService.getAllSimpleProducts();
        List<Product> productList1 = productService.getAllPizza();
        List<Product> productList2 = productService.getAllComboProduct();

        MenuCardGenerator generator = new MenuCardGenerator(productList,productList1,productList2);
        generator.export(res);
    }
}
