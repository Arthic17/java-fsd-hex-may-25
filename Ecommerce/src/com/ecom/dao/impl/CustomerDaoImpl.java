package com.ecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecom.dao.CustomerDao;
import com.ecom.exception.InvalidIdException;
import com.ecom.model.Category;
import com.ecom.model.Customer;
import com.ecom.model.Product;
import com.ecom.utility.DBUtility;

public class CustomerDaoImpl implements CustomerDao{
	DBUtility db = new DBUtility();
	
	@Override
	public Customer getById(int id) throws InvalidIdException {
			Connection con = db.connect();
			String sql="select * from customer where id=?";
			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rst=  pstmt.executeQuery();
				if(rst.next() == true) {
					 Customer customer = new Customer();
					 customer .setId(rst.getInt("id"));
					 customer .setName(rst.getString("name"));
					 customer.setCity(rst.getString("city"));
					return  customer; 
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			db.close();
			throw new InvalidIdException("ID given is Invalid");
		}
	}


