package com.oorja.ProductBasedApplication.Controller;

import com.oorja.ProductBasedApplication.Models.Coupon;
import com.oorja.ProductBasedApplication.Models.Product;
import com.oorja.ProductBasedApplication.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductServices productServices;
    @PostMapping(value= {"/product/create"})
    public String productCoupon(@RequestBody Product product){
        productServices.addProduct(product);
        return "product added successfully";
    }
}
