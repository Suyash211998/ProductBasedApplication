package com.oorja.ProductBasedApplication.Services;

import com.oorja.ProductBasedApplication.Dao.OrdersDao;
import com.oorja.ProductBasedApplication.Exceptions.ResourceNotFoundExceptions;
import com.oorja.ProductBasedApplication.Models.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServicesImpl implements OrdersServices{

    @Autowired
    OrdersDao orderDao;
    @Override
    public void addOrder(Orders order) {
        orderDao.save(order);
    }

    @Override
    public Orders getOrder(int id) {
        Orders order = orderDao.findById(id).orElseThrow(()-> new ResourceNotFoundExceptions("Order", "id", id));
        return order;
    }

    @Override
    public String updateOrderStatus(String status, int id) {
        Orders order = getOrder(id);
        if(order != null){
            order.setStatus(status);
            orderDao.save(order);
            return "Updated Successfully";
        }
        return "Invalid credentials";
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public boolean isExistsOrderByUserNameAndCoupon(String username, String coupon) {
        return orderDao.existsByUserNameAndCouponName(username, coupon);
    }
}
