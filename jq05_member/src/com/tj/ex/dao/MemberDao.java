package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.Member;

public class MemberDao {
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	private MemberDao() {}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	//가입하기
	public int join(Member dto) {
		int result = 0;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,dto.getId     ());
			pstmt.setString(2,dto.getPw     ());
			pstmt.setString(3,dto.getName   ());
			pstmt.setDate  (4,dto.getBirth  ());
			pstmt.setString(5,dto.getGender ());
			pstmt.setString(6,dto.getEmail  ());
			pstmt.setString(7,dto.getPhone  ());
			pstmt.setString(8,dto.getAddress());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	
	//id 중복 chk
	public int idChk(String id) {
		int result = 0;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE ID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println(result);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
	//email 중복 chk
	public int emailChk(String email) {
		int result = 0;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE EMAIL=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println(result);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return result;
	}
}
