package com.ecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecom.dao.ProductDao;
import com.ecom.exception.InvalidIdException;
import com.ecom.model.Product;
import com.ecom.model.*;
import com.ecom.utility.DBUtility;



public class ProductDaoImpl implements ProductDao{
	
	DBUtility db = new DBUtility();
	@Override
	public void insert(Product product) {
		
			Connection con = db.connect();
			String sql = "insert into product values (?,?,?,?,?)";
			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, product.getId());
				pstmt.setString(2, product.getTitle());
				pstmt.setDouble(3, product.getPrice());
				pstmt.setString(4, product.getDescription());
				pstmt.setInt(5, product.getCategory().getId());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			db.close();
			
	}
	

	@Override
	public List<Product> getByCategoryId(int categoryId) throws InvalidIdException {
			Connection con=db.connect();
			String sql="Select * from product p join category c on p.category_id=c.id where c.id=?";
			 List<Product> list=new ArrayList<>();
			 try {
				 PreparedStatement pstmt=con.prepareStatement(sql);
				 pstmt.setInt(1, categoryId);
				 ResultSet rst=pstmt.executeQuery();
				 while(rst.next()) {
					 Product product=new Product();
					 product.setId(rst.getInt("id"));
					 product.setTitle(rst.getString("title"));
					 product.setPrice(rst.getDouble("price"));
					 product.setDescription(rst.getString("description"));
					 Category category=new Category();
					 category.setId(rst.getInt("category_id"));
					 category.setName(rst.getString("name"));
					 product.setCategory(category);
					 list.add(product);
					 
				 }
				 if(list.isEmpty()) {
					 throw new InvalidIdException("Invalid Category ID : No products found");
				 }
			 }catch(SQLException e) {
				 System.out.println(e.getMessage());
			 }
			 db.close();
				return list;
		
	}
	
	
	@Override
	public Product getById(int id) throws InvalidIdException {
		Connection con = db.connect();
		String sql="select * from product p join category c on p.category_id=c.id where p.id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rst=  pstmt.executeQuery();
			if(rst.next() == true) {
				Product product = new Product();
				 product .setId(rst.getInt("id"));
				 product .setTitle(rst.getString("title"));
				 product.setPrice(rst.getDouble("price"));
				 product.setDescription(rst.getString("description"));
				 Category category=new Category();
				 category.setId(rst.getInt("category_id"));
				 product.setCategory(category);
				return product; 
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.close();
		throw new InvalidIdException("ID given is Invalid");
	}

}
