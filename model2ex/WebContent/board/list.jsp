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
	<c:set var="SUCCESS" value="1"/>
	<c:set var="FAIL" value="0"/>
	<c:if test="${writeResult eq SUCCESS }">
		<script>alert('글쓰기 성공');</script>
	</c:if>
	<c:if test="${writeResult eq FAIL }">
		<script>alert('글쓰기 실패');</script>
	</c:if>
	<c:if test="${modifyResult eq SUCCESS }">
		<script>alert('글수정 성공');</script>
	</c:if>
	<c:if test="${modifyResult eq FAIL }">
		<script>alert('글수정 실패');</script>
	</c:if>
	<c:if test="${deleteResult eq SUCCESS }">
		<script>alert('글삭제 성공');</script>
	</c:if>
	<c:if test="${deleteResult eq FAIL }">
		<script>alert('글삭제 실패');</script>
	</c:if>
	<c:if test="${not empty member and empty admin}">
		<table>
		<tr><td>
			<a href="${conPath }/bWrite_view.do?fId=${dto.fId}&pageNum=${pageNum}">글쓰기</a>
		</td></tr>
		</table>
	</c:if>
	<table>
		<tr><th>글번호</th><th>작성자</th><th>글제목</th><th>조회수</th><th>날짜</th><th>ip</th></tr>
		<c:if test="${list.size() eq 0 }">
			<td colspan="6">해당 페이지의 글이 없습니다.</td>
		</c:if>
		<c:if test="${list.size() != 0 }">
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.fId 	}</td>
					<td>${dto.mName }</td>
					<td class="left">
						<c:forEach var="i" begin="1" end="${dto.fIndent }">
							<c:if test="${i < dto.fIndent}">
							&nbsp; &nbsp; &nbsp; 
							</c:if>
							<c:if test="${i eq dto.fIndent}">
							┗
							</c:if>
						</c:forEach>
						<a href="${conPath }/content_view.do?fId=${dto.fId}&pageNum=${pageNum}">${dto.fTitle }</a>
					</td>
					<td>${dto.fHit 	}</td>
					<td><fmt:formatDate value="${dto.fRdate }" pattern="yy. M. d"/></td>
					<td>${dto.fIp 	}</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE}">
			<a href="${conPath }/list.do?pageNum=${startPage-1 }">이전</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i eq pageNum }">
				[ <b> ${i }</b> ]
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/list.do?pageNum=${i }"> ${i }</a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage < pageCnt}">
			<a href="${conPath }/list.do?pageNum=${endPage+1 }">다음</a>
		</c:if>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>