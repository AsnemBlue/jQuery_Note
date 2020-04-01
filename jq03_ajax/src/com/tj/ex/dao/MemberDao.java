package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.MemberDto;

public class MemberDao {
	public static final int EXISTENT = 1;
	public static final int NONEXISTENT =0;
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	private MemberDao() {}
	private Connection getConnection() throws SQLException  {
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
	
	//mid중복체크
	public int mIdConfirm(String mId) {
		int result = EXISTENT;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT * FROM MVC_MEMBER WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=EXISTENT;
			}else {
				result=NONEXISTENT;
			}
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
	//?��?���??��
	public int joinMember(MemberDto dto) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MVC_MEMBER (MID, MPW ,MNAME ,MEMAIL ,MPHOTO ,MBIRTH, MADDRESS) " + 
					 "VALUES(?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId() 		);
			pstmt.setString(2, dto.getmPw() 		);
			pstmt.setString(3, dto.getmName()		);
			pstmt.setString(4, dto.getmEmail() 		);
			pstmt.setString(5, dto.getmPhoto() 		);
			pstmt.setDate  (6, dto.getmBirth() 		);
			pstmt.setString(7, dto.getmAddress()	);
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
	//로그?��
	public int loginCheck(String mId, String mPw) {
		int result = LOGIN_FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT * FROM MVC_MEMBER WHERE MID=? AND MPW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=LOGIN_SUCCESS;
			}else {
				result=LOGIN_FAIL;
			}
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
	//ID�? DTO�??��?���?
	public MemberDto getMemberDto(String mId){
		MemberDto dto = new MemberDto();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT * FROM MVC_MEMBER WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mPw      =rs.getString("mPw"		);
				String mName    =rs.getString("mName"	);
				String mEmail   =rs.getString("mEmail"	);
				String mPhoto   =rs.getString("mPhoto"	);
				Date   mBirth   =rs.getDate  ("mBirth"	);
				String mAddress =rs.getString("mAddress");
				Date   mRdate   =rs.getDate  ("mRdate"	);
				dto = new MemberDto(mId, mPw, mName, mEmail, mPhoto, mBirth, mAddress, mRdate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return dto;
	}
	public int modifyMember(MemberDto dto) {
		int result = FAIL;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MVC_MEMBER SET MPW=?, MNAME=?, MEMAIL=?, "+
					 "MPHOTO=?, MBIRTH=?,MADDRESS=? WHERE MID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmPw() 		);
			pstmt.setString(2, dto.getmName()		);
			pstmt.setString(3, dto.getmEmail() 		);
			pstmt.setString(4, dto.getmPhoto() 		);
			pstmt.setDate  (5, dto.getmBirth() 		);
			pstmt.setString(6, dto.getmAddress()	);
			pstmt.setString(7, dto.getmId() 		);
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
	//목록불러?���?
	public ArrayList<MemberDto> listMembers(int startrow, int endrow){
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MVC_MEMBER ORDER BY MRDATE DESC) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mId      =rs.getString("mId"		);
				String mPw      =rs.getString("mPw"		);
				String mName    =rs.getString("mName"	);
				String mEmail   =rs.getString("mEmail"	);
				String mPhoto   =rs.getString("mPhoto"	);
				Date   mBirth   =rs.getDate  ("mBirth"	);
				String mAddress =rs.getString("mAddress");
				Date   mRdate   =rs.getDate  ("mRdate"	);
				dtos.add(new MemberDto(mId, mPw, mName, mEmail, mPhoto, mBirth, mAddress, mRdate));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	!=null) rs	 .close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (Exception e2) {}
		}
		return dtos;
	}
	//?��체명?��
	public int countMembers() {
		int result = 0;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM MVC_MEMBER";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
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
