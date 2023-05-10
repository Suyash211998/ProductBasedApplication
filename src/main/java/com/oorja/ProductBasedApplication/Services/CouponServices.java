package com.oorja.ProductBasedApplication.Services;

import com.oorja.ProductBasedApplication.Models.Coupon;

import java.util.List;

public interface CouponServices {

    void addCoupon(Coupon coupon);

    void deleteCoupon(int id);

    Coupon getByVoucher(String voucherCode);

    Coupon getById(int id);

    List<Coupon> getAllCoupons();
}
