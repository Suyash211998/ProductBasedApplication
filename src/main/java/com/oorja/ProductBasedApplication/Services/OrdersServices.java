package com.oorja.ProductBasedApplication.Services;

import com.oorja.ProductBasedApplication.Models.Orders;

import java.util.List;

public interface OrdersServices {

    void addOrder(Orders order);

    Orders getOrder(int id);

    String updateOrderStatus(String status, int id);

    List<Orders> getAllOrders();

    boolean isExistsOrderByUserNameAndCoupon(String username, String coupon);
}
