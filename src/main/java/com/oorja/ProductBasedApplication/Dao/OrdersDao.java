package com.oorja.ProductBasedApplication.Dao;

import com.oorja.ProductBasedApplication.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDao extends JpaRepository<Orders,Integer> {
    boolean existsByUserNameAndCouponName(String userName, String couponName);

}
