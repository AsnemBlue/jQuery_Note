<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<form action="${conPath }/bReply.do?pageNum=${param.pageNum }" method="post" enctype="multipart/form-data">
		<input type="hidden" name="mId" value="${member.mId }">
		<input type="hidden" name="fId" value="${param.fId }">
		<table>
			<caption>답변 쓰기</caption>
			<tr>
				<th>작성자</th><th><input type="text" name="mName" readonly="readonly" value="${member.mName} "></th>
			</tr>
			<tr>
				<th>제목</th><th><input type="text" name="fTitle" required="required"></th>
			</tr>
			<tr>
				<th>첨부파일</th><th><input type="file" name="fFileName" ></th>
			</tr>
			<tr>
				<th>본문</th><th><textarea name="fContent" rows="5" cols="20"></textarea></th>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="완료" class="btn">
					<input type="reset" value="취소" class="btn">
					<input type="button" value="뒤로" class="btn" onclick="history.back()">
				</td>
			</tr>
			
		</table>
	</form>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>