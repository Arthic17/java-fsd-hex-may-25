package com.ecom.dao;

import com.ecom.exception.InvalidIdException;
import com.ecom.model.Purchase;

public interface PurchaseDao {
	void insert(Purchase purchase);
}
