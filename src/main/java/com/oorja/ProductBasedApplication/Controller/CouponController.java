package com.oorja.ProductBasedApplication.Controller;

import com.oorja.ProductBasedApplication.Models.Coupon;
import com.oorja.ProductBasedApplication.Services.CouponServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

    @Autowired
    CouponServices couponServices;

    @PostMapping(value= {"/coupon/create"})
    public String createCoupon(@RequestBody Coupon coupon){
        couponServices.addCoupon(coupon);
        return "Coupon added successfully";
    }
}
