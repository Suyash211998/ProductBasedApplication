package com.oorja.ProductBasedApplication.Dao;

import com.oorja.ProductBasedApplication.Models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponDao extends JpaRepository<Coupon, Integer> {

    public Coupon findByVoucherCode(String voucherCode);
}
