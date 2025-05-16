package com.ecom.service;

import java.time.LocalDate;
import java.util.Scanner;

import com.ecom.exception.InvalidIdException;
import com.ecom.model.*;
import com.ecom.enums.Coupon;
import com.ecom.dao.*;
import com.ecom.dao.impl.*;


public class PurchaseService {
	private CustomerDao customerDao = new CustomerDaoImpl(); 
	private ProductDao productDao = new ProductDaoImpl();
	private PurchaseDao purchaseDao = new PurchaseDaoImpl(); 
	
	public void insert(int customer_id, int product_id, Scanner sc) throws InvalidIdException {
		
		Purchase purchase = new Purchase(); 
		Customer customer =  customerDao.getById(customer_id);
		purchase.setCustomer(customer);
		Product product =  productDao.getById(product_id); 
		purchase.setProduct(product);
		System.out.println("Do you have any coupon?(YES/NO) ");
		String ans = sc.next();
		if(ans.equals("YES")) {
			System.out.println("Enter the coupon code ");
			String couponCode=sc.next().toUpperCase();
			Coupon coupon=Coupon.valueOf(couponCode);
			double discount=(double)coupon.getDiscount();
			System.out.println("Discount = " + discount);
			double discountedPrice = product.getPrice()-(product.getPrice()*(discount/100)) ;
			System.out.println("After Discount, Price is " + discountedPrice);
			purchase.setCoupon(coupon);
			purchase.setAmount_paid(discountedPrice);
			
		}
		else {
			System.out.println("No Coupon applied.....");
			purchase.setAmount_paid(product.getPrice());
		}
		
		purchase.setDate_of_purchase(LocalDate.now().toString()); 
		purchaseDao.insert(purchase);
		 
	}
}
