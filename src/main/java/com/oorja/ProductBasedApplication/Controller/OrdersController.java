package com.oorja.ProductBasedApplication.Controller;

import com.oorja.ProductBasedApplication.Helper.Holiday;
import com.oorja.ProductBasedApplication.Models.Coupon;
import com.oorja.ProductBasedApplication.Models.Orders;
import com.oorja.ProductBasedApplication.Services.CouponServices;
import com.oorja.ProductBasedApplication.Services.OrdersServices;

import com.oorja.ProductBasedApplication.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class OrdersController {

    @Autowired
    OrdersServices ordersServices;

    @Autowired
    CouponServices couponServices;

    @Autowired
    ProductServices productServices;

    // create an order
    @PostMapping(value= {"/orders/create"})
    public String createOrder( @RequestBody Orders order) throws ParseException {
        Coupon coupon = couponServices.getByVoucher(order.getCouponName());
        if(!isOrderInRange(order.getMaxPrice()) || !isProductAvailable(order.getProductId())){
            return "Total Price is Not in Range (99-4999) or product is not available";
        }
        else if(coupon != null && isValidCoupon(order)){
            double discount = coupon.getDiscount() /100* order.getMaxPrice();
            order.setDiscount(discount);
            order.setTotalPrice(order.getMaxPrice() - discount);
            ordersServices.addOrder(order);
            productServices.updateProductQuantity(1,order.getProductId());
            return "Order is Created Successfully with discount";
        }
        else{
            order.setDiscount(0);
            order.setTotalPrice(order.getMaxPrice());
            order.setCouponName("Not Applicable");
            ordersServices.addOrder(order);
            productServices.updateProductQuantity(1,order.getProductId());
            return "Order is Created Successfully";
        }



    }

    // Get Order By Id!

    @GetMapping(value={"/orders/getById/{id}"})
    public Orders orderGetById(@PathVariable int id){
        return ordersServices.getOrder(id);
    }

    //Update Order Status.
    @PutMapping(value={"/orders/getByIdStatus/{status}/{id}"})
    public String UpdateOrderStatus(@PathVariable String status,@PathVariable int id){
        return ordersServices.updateOrderStatus(status,id);
    }


    //Check order Price Range
    public boolean isOrderInRange(double price){
        return price >= 99 && price <= 4999;
    }

    // check product quantity
    public boolean isProductAvailable(int productId){
        System.out.println(productServices.getProductById(productId).getQuantity() >0);
        return productServices.getProductById(productId).getQuantity() >0;
    }


    // Check Coupon is valid or not.
    public boolean isValidCoupon(Orders order) throws ParseException {
        Coupon coupon = couponServices.getByVoucher(order.getCouponName());
        return isExpiredCheck(order.getBookDate(), coupon.getEndDate()) && !isSundayOrPublicDay()
                && !isUserExist(order.getUserName(), order.getCouponName());
    }

    // Check Coupon is Expired Or Not.
    public boolean isExpiredCheck(String currentDate, String expiredDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = dateFormat.parse(currentDate);
        Date endDate = dateFormat.parse(expiredDate);

        System.out.println(nowDate.compareTo(endDate) < 0);
        return nowDate.compareTo(endDate) < 0;
    }

    // Check day is Sunday or publicHoliday
    public boolean isSundayOrPublicDay(){
        Format f = new SimpleDateFormat("EEEE");
        String str = f.format(new Date());
        return str.equals("Sunday") || Holiday.isPublicDay();
    }

    //check the user who claimed offer already.
    public boolean isUserExist(String username, String coupon){
        System.out.println(ordersServices.isExistsOrderByUserNameAndCoupon(username,coupon));
        return ordersServices.isExistsOrderByUserNameAndCoupon(username,coupon);
    }

    // check offer valid or not
    @GetMapping(value={"/orders/checkOffer/{couponName}"})
    public String validCoupon(@PathVariable String couponName) {
        try{
            Date date = Calendar.getInstance().getTime();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            String dateInString=df.format(date);

            Coupon coupon = couponServices.getByVoucher(couponName);
            if(!isExpiredCheck(dateInString, coupon.getEndDate())){
                return "offer Expired";
            }
            else if(isSundayOrPublicDay()) {
                return "Sunday or Public Holiday";
            }
            return "Validated";
        }
        catch (NullPointerException | ParseException ex){
            ex.getMessage();
        }
        return "Exceptions";
    }




}
