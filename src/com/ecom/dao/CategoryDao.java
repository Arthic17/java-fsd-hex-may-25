package com.ecom.dao;

import com.ecom.exception.InvalidIdException;
import com.ecom.model.Category;


public interface CategoryDao {
	Category getById(int id) throws InvalidIdException;
	
}
