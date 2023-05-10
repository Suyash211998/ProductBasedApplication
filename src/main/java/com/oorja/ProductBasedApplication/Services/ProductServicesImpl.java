package com.oorja.ProductBasedApplication.Services;

import com.oorja.ProductBasedApplication.Dao.ProductDao;
import com.oorja.ProductBasedApplication.Exceptions.ResourceNotFoundExceptions;
import com.oorja.ProductBasedApplication.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServicesImpl implements ProductServices{

    @Autowired
    private ProductDao productDao;

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public Product getProductById(int id) {
        return   productDao.findById(id).orElseThrow(()-> new ResourceNotFoundExceptions("Product", "id", id));
    }

    @Override
    public void updateProductQuantity(int quantity, int id) {
        Product product = getProductById(id);
        if(product != null){
            product.setQuantity(product.getQuantity()-quantity);
            productDao.save(product);
        }
    }

}
