<%@page import="com.tj.ex.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	String mId = request.getParameter("mId");
	MemberDao mDao = MemberDao.getInstance();
	int result = mDao.mIdConfirm(mId);
	if(result==MemberDao.EXISTENT){
		out.print("�ߺ��� ID�Դϴ�.");
	}else{
		out.print("��� ������ ID�Դϴ�.");
		
	}
%>
</body>
</html>