package com.oorja.ProductBasedApplication.Services;

import com.oorja.ProductBasedApplication.Dao.CouponDao;
import com.oorja.ProductBasedApplication.Exceptions.ResourceNotFoundExceptions;
import com.oorja.ProductBasedApplication.Models.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServicesImpl implements CouponServices{

    @Autowired
    private CouponDao couponDao;


    @Override
    public void addCoupon(Coupon coupon) {
        couponDao.save(coupon);
    }

    @Override
    public void deleteCoupon(int id) {
        couponDao.deleteById(id);
    }

    @Override
    public Coupon getByVoucher(String voucherCode) {
        return couponDao.findByVoucherCode(voucherCode);
    }

    @Override
    public Coupon getById(int id) {
        return couponDao.findById(id).orElseThrow(()-> new ResourceNotFoundExceptions("Coupon", "id", id));
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponDao.findAll();
    }
}
