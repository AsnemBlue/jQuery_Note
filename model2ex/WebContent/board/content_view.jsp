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
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<form>
		<input type="hidden" value="${content_view.fId}">
	</form>
	<table>
		<caption>${content_view.fId }번 글 상세보기</caption>
		<tr><th>제목</th><td>${content_view.fTitle }</td></tr>
		<tr><th>작성자</th><td>${content_view.mName }</td></tr>
		<tr><th>첨부파일</th><td>
		<c:if test="${content_view.fFileName != null}">
			<a href="${conPath }/board_file/${content_view.fFileName}" target="_blank">${content_view.fFileName} </a>
		</c:if>
		</td></tr>
		<tr><th>본문</th><td><pre>${content_view.fContent }</pre></td></tr>
		<tr><th>작성일</th>
			<td><fmt:formatDate value="${content_view.fRdate }" pattern="yy/MM/dd(E)"/></td>
		</tr>
		<tr><th>조회수</th><td>${content_view.fHit }</td></tr>
		<tr><th>IP</th><td>${content_view.fIp }</td></tr>
		<tr><td colspan="2">
			<c:if test="${not empty member and member.mId eq content_view.mId}">
			<button onclick="location='${conPath }/modify_view.do?fId=${param.fId }&pageNum=${param.pageNum }'">
			수정</button>
			</c:if>
			<button onclick="location='${conPath }/reply_view.do?fId=${param.fId }&pageNum=${param.pageNum }'">
			답변</button>
			<c:if test="${not empty member and member.mId eq content_view.mId}">
			<button onclick="location='${conPath }/bdelete.do?fId=${param.fId }&pageNum=${param.pageNum }'">
			삭제</button>
			</c:if>
			<button onclick="location='${conPath }/list.do?fId=${param.fId }&pageNum=${param.pageNum }'">
			목록</button>
	</table>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>