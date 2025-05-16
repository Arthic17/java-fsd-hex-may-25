package com.ecom.enums;


public enum Coupon {
	YEAREND(5),
	MEGADEAL(10),
	FLASHSALE(15),
	FIRSTBUY(20);
	
	Coupon(int discount){
		this.discount = discount;
	}
	
	private int discount;

	public int getDiscount() {
		return discount;
	} 
	
	
}
