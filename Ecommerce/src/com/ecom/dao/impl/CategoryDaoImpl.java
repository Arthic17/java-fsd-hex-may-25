package com.ecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecom.dao.CategoryDao;
import com.ecom.exception.InvalidIdException;
import com.ecom.model.Category;
import com.ecom.utility.DBUtility;

public class CategoryDaoImpl implements CategoryDao{
	
	DBUtility db = new DBUtility();

	@Override
	public Category getById(int id) throws InvalidIdException {

			Connection con = db.connect();
			String sql="select * from category where id=?";
			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rst=  pstmt.executeQuery();
				if(rst.next() == true) {
					 Category category=new Category();
					 category.setId(rst.getInt("id"));
					 category .setName(rst.getString("name"));
					return  category; 
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			db.close();
			throw new InvalidIdException("ID given is Invalid");
		}
}
