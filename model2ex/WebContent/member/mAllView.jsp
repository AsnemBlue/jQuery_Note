<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	#content_form {
		width: 800px; height:500px; 
		margin: 0 auto; text-align: center;
	}
	img{width: 200px; height: 250px; text-align: center;}
</style>
<c:if test="${not empty errorMsg }">
	<script>
		alert('${errorMsg }');
		history.back();
	</script>
</c:if>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table>
			<caption>전체회원보기</caption>
			<c:if test="${list.size() != 0 }">
				<tr>
					<c:forEach items="${list }" var="dto">
						<td>
							<p><img src="${conPath }/member_photo/${dto.mPhoto}"></p>
							<p>${dto.mName }</p>
							<p>(${dto.mId })</p>
						</td>
					</c:forEach>
				</tr>
			</c:if>
		</table>
		<div class="paging">
		<c:if test="${startPage > BLOCKSIZE}">
			<a href="${conPath }/alogin.do?pageNum=${startPage-1 }">이전</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i eq pageNum }">
				[ <b> ${i }</b> ]
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/alogin.do?pageNum=${i }"> ${i }</a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage < pageCnt}">
			<a href="${conPath }/alogin.do?pageNum=${endPage+1 }">다음</a>
		</c:if>
	</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>