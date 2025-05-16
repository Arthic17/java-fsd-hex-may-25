package com.ecom.model;

import com.ecom.enums.Coupon;

public class Purchase {
private int id;
private String date_of_purchase;
private Product product;
private Customer customer;
private Coupon coupon;
private double amount_paid;



public Purchase() { }

public Purchase(int id, String date_of_purchase, Coupon coupon,double amount_paid, Product product, Customer customer) {
	super();
	this.id = id;
	this.date_of_purchase = date_of_purchase;
	this.coupon=coupon;
	this.amount_paid=amount_paid;
	this.product = product;
	this.customer = customer;
}

public double getAmount_paid() {
	return amount_paid;
}

public void setAmount_paid(double amount_paid) {
	this.amount_paid = amount_paid;
}
public int getId() {
	return id;
}

public String getDate_of_purchase() {
	return date_of_purchase;
}
public Coupon getCoupon() {
	return coupon;
}

public Product getProduct() {
	return product;
}

public Customer getCustomer() {
	return customer;
}

public void setId(int id) {
	this.id = id;
}

public void setDate_of_purchase(String date_of_purchase) {
	this.date_of_purchase = date_of_purchase;
}
public void setCoupon(Coupon coupon) {
	this.coupon = coupon;
}

public void setProduct(Product product) {
	this.product = product;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

@Override
public String toString() {
	return "Purchase [id=" + id + ", date_of_purchase=" + date_of_purchase + ", coupon="
			+ coupon + ", amount_paid="
					+ amount_paid + ", product=" + product
			+ ", customer=" + customer + "]";
}





}
