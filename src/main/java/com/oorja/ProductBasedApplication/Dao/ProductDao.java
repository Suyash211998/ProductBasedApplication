package com.oorja.ProductBasedApplication.Dao;

import com.oorja.ProductBasedApplication.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
}
