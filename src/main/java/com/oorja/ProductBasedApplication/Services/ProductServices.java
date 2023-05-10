package com.oorja.ProductBasedApplication.Services;

import com.oorja.ProductBasedApplication.Models.Product;

import java.util.Optional;

public interface ProductServices {

    void addProduct(Product product);

    Product getProductById(int id);

    void updateProductQuantity(int quantity, int id);
}
