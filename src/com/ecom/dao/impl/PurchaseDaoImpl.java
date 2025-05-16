package com.ecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ecom.dao.PurchaseDao;
import com.ecom.exception.InvalidIdException;
import com.ecom.model.Purchase;
import com.ecom.utility.DBUtility;

public class PurchaseDaoImpl implements PurchaseDao{

	@Override
	public void insert(Purchase purchase) {
        DBUtility db = new DBUtility();
		Connection con = db.connect();
		String sql = "insert into purchase(id,customer_id,product_id,date_of_purchase,coupon_used,amount_paid) values (?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, purchase.getId());
			pstmt.setInt(2, purchase.getCustomer().getId());
			pstmt.setInt(3, purchase.getProduct().getId());
			pstmt.setString(4, purchase.getDate_of_purchase().toString());
			pstmt.setString(5, String.valueOf(purchase.getCoupon()));
			pstmt.setDouble(6, purchase.getAmount_paid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.close();

	}

}
