package com.ecom.model;

import com.ecom.enums.Coupon;

public class Product {
 private int id;
 private String title;
 private double price;
 private String description;
 private Category category;

 
public Product() { }


public Product(int id, String title, double price,String description,Category category) {
	super();
	this.id = id;
	this.title = title;
	this.price = price;
	this.description = description;
	this.category = category;
}


public int getId() {
	return id;
}


public String getTitle() {
	return title;
}


public double getPrice() {
	return price;
}



public String getDescription() {
	return description;
}


public Category getCategory() {
	return category;
}


public void setId(int id) {
	this.id = id;
}


public void setTitle(String title) {
	this.title = title;
}


public void setPrice(double price) {
	this.price = price;
}


public void setDescription(String description) {
	this.description = description;
}


public void setCategory(Category category) {
	this.category = category;
}


@Override
public String toString() {
	return "Product [id=" + id + ", title=" + title + ", price=" + price +  ", description=" + description
			+ ", category=" + category + "]";
}
 
 
 
 
 
 
}
