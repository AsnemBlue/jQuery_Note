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

import com.tj.ex.dto.BoardDto;


public class BoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static BoardDao instance = new BoardDao();
	public static BoardDao getInstance() {
		return instance;
	}
	private BoardDao() {}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	//글목록(startRow부터 endRow까지)
	public ArrayList<BoardDto> bList(int startrow, int endrow){
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " +
				     "(SELECT F.*, MNAME FROM FILEBOARD F, MVC_MEMBER M WHERE F.MID=M.MID ORDER BY FGROUP desc, FSTEP) A) " +
				     "WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int 	fId      	=rs.getInt		("fId"		);
				String 	mId      	=rs.getString	("mId"		);
				String 	mName   	=rs.getString	("mName"	);
				String 	fTitle   	=rs.getString	("fTitle"	);
				String 	fContent 	=rs.getString	("fContent"	);
				String 	fFileName	=rs.getString	("fFileName");
				Date	fRdate   	=rs.getDate		("fRdate"	);
				int   	fHit   	 	=rs.getInt  	("fHit"		);
				int   	fGroup   	=rs.getInt  	("fGroup"	);
				int   	fStep   	=rs.getInt  	("fStep"	);
				int   	fIndent   	=rs.getInt  	("fIndent"	);
				String 	fIp 		=rs.getString	("fIp"		);
				dtos.add(new BoardDto(fId, mId, mName, fTitle, fContent, fFileName, fRdate, fHit, fGroup, fStep, fIndent, fIp));
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
	//글갯수
	public int bCount() {
		int result = 0;
		Connection 		  conn 	= null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs 	= null;
		String sql = "SELECT COUNT(*) FROM FILEBOARD";
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
	//글쓰기(원글)
	public int bWrite(String mId, String fTitle, String fContent, String fFileName, String fIp) {
		int result = 0;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql =" INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP) " + 
					" VALUES (FILEBOARD_SEQ.NEXTVAL, ?,?,?,?, FILEBOARD_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId		);
			pstmt.setString(2, fTitle	);
			pstmt.setString(3, fContent	);
			pstmt.setString(4, fFileName);
			pstmt.setString(5, fIp		);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt .close();
				if(conn  != null) conn  .close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	} 
	//FHit 하나 올리기(1번글 조회수 하나 올리기)
	public void bHit(int fId) {
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		String sql ="UPDATE FILEBOARD SET FHIT = FHIT+1 WHERE FID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt .close();
				if(conn  != null) conn  .close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	//fId로 글 dto보기
	public BoardDto bGetDto(int fId) {
		BoardDto 		  dto 	= null;
		Connection		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT F.*, MNAME FROM FILEBOARD F, MVC_MEMBER M WHERE F.MID=M.MID AND FID=?";
		try {
			conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			rs = pstmt.executeQuery();
			if(rs.next()) {                                                                                                    
				String 	mId      	=rs.getString	("mId"		);                                                                
				String 	mName   	=rs.getString	("mName"	);                                                                
				String 	fTitle   	=rs.getString	("fTitle"	);                                                                
				String 	fContent 	=rs.getString	("fContent"	);                                                                
				String 	fFileName	=rs.getString	("fFileName");                                                                
				Date	fRdate   	=rs.getDate		("fRdate"	);                                                                
				int   	fHit   	 	=rs.getInt  	("fHit"		);                                                                
				int   	fGroup   	=rs.getInt  	("fGroup"	);                                                                
				int   	fStep   	=rs.getInt  	("fStep"	);                                                                
				int   	fIndent   	=rs.getInt  	("fIndent"	);                                                                
				String 	fIp 		=rs.getString	("fIp"		);                                                                
				dto = new BoardDto(fId, mId, mName, fTitle, fContent, fFileName, fRdate, fHit, fGroup, fStep, fIndent, fIp);  
			}                                                                                                                     
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs	 != null) rs	.close();
				if(pstmt != null) pstmt .close();
				if(conn  != null) conn  .close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return dto;
	}
	//글 수정하기(FId, FTitle, FContent, FILENAME,  FIp, FDATE)
	public int bUpdate(int fId, String fTitle, String fContent, String fFileName, String fIp) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FILEBOARD SET FTITLE=?, FCONTENT=?, FFILENAME=?, FIp=?  WHERE FID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fTitle	);
			pstmt.setString(2, fContent	);
			pstmt.setString(3, fFileName);
			pstmt.setString(4, fIp		);
			pstmt.setInt   (5, fId		);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	//글 삭제하기(bId로 삭제하기)
	public int bDelete(int fId) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM FILEBOARD WHERE FID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt   (1, fId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
	//   답변글 추가전 STEP a 수행
	private void bStepA(int fStep, int fGroup) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FILEBOARD SET FSTEP=FSTEP+1 WHERE FSTEP>? AND fgroup=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fStep);
			pstmt.setInt(2, fGroup);
			int result = pstmt.executeUpdate();
			if(result>0) {
				System.out.println("stepA success");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	//답변글 쓰기
	public int reply(BoardDto dto) {
		int result = 0;
		bStepA(dto.getfStep(), dto.getfGroup());
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP) " + 
					 "VALUES (FILEBOARD_SEQ.NEXTVAL, ?,?,?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId()			);
			pstmt.setString(2, dto.getfTitle()		);
			pstmt.setString(3, dto.getfContent()	);
			pstmt.setString(4, dto.getfFileName()	);
			pstmt.setInt   (5, dto.getfGroup ()		);
			pstmt.setInt   (6, dto.getfStep	 ()+1	);
			pstmt.setInt   (7, dto.getfIndent()+1	);
			pstmt.setString(8, dto.getfIp()			);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("글쓰기 예외 : "+dto.toString());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn .close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		return result;
	}
}
