package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.ex.dto.ProductDto;

public class ProductDao {
	private static ProductDao instance = new ProductDao();
	public static ProductDao getInstance() {
		return instance;
	}
	private ProductDao() {}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public ProductDto getProduct(String pname) {
		ProductDto product = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PRODUCT WHERE PNAME=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				product = new ProductDto();
				product.setPno(rs.getInt("pno"));
				product.setPname(pname);
				product.setPstock(rs.getInt("pstock"));
				
				System.out.println("성공");
			}else {
				System.out.println("실패");
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return product;
	}
}
