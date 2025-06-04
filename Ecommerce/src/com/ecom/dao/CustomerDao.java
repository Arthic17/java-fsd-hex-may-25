package com.ecom.dao;

import com.ecom.exception.InvalidIdException;
import com.ecom.model.*;

public interface CustomerDao {
	Customer getById(int id) throws InvalidIdException;
}
