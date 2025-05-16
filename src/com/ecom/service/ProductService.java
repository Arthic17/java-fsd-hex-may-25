package com.ecom.service;


import java.util.List;

import com.ecom.model.*;
import com.ecom.dao.*;
import com.ecom.dao.impl.*;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;

public class ProductService {
	private ProductDao productDao=new ProductDaoImpl();
	
	public void insertProduct(Product product,int categoryId) throws InvalidIdException,InvalidInputException {
		if (product.getTitle() == null || product.equals("null")) {
	        throw new InvalidInputException("Product name cannot be empty");
	    }
		int id=(int)(Math.random()*10000000);
		product.setId(id);
		productDao.insert(product, categoryId);
	}
	
	
	public List<Product> getByCategoryId(int categoryId) throws InvalidIdException {
		return productDao.getByCategoryId(categoryId);
	}

}
