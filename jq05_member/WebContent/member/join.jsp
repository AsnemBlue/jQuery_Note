<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link href='../css/join.css' rel='stylesheet'>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(document).ready(function(){
		$('#id').keyup(function(){
			$.ajax({
				url : '../idChk.do',
				type : 'get',
				dataType : 'html',
				data : 'id='+$('input[name="id"]').val(),
				success : function(data,status){
					$('#idChkMsg').html(data);
				}
			})
		});
		$('#email').keyup(function(){
			$.ajax({
				url : '../emailChk.do',
				type : 'get',
				dataType : 'html',
				data : 'email='+$('input[name="email"]').val(),
				success : function(data,status){
					$('#emailChkMsg').html(data);
				}
			})
		});
		$('#pwChk').keyup(function(){
			if($('#pw').val()!=$('#pwChk').val()){
				$('#pwChkMsg').html("비밀번호가 불일치 합니다");
			}else{
				$('#pwChkMsg').html("비밀번호가 일치 합니다");
			}
		});
		$('form').submit(function(){
		});
		
		 $( "#datepicker" ).datepicker({
		    	dateFormat : 'yy-mm-dd',
		    	monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    	showMonthAfterYear : true,
		    	yearSuffix : '년',
		    	showOtherMonths: true,
		        selectOtherMonths: true,
		        maxDate : 0
		 });

	});
</script>
</head>
<body>
	<div id="joinForm_wrap">
		<form action="${conPath}/join.do" method="post">
			<div id="join_title">회원가입</div>
			<table>
				<tr>
					<th><label for="id">아이디</label></th>
					<td><input type="text" name="id" id="id" class="id"></td>
				</tr>
				<tr>
					<td colspan="2" id="idChkMsg"></td>
				</tr>
				<tr>
					<th><label for="pw">비밀번호</label></th>
					<td><input type="password" name="pw" id="pw" class="pw"></td>
				</tr>
				<tr>
					<th><label for="pwChk">비밀번호확인</label></th>
					<td><input type="password" name="pwChk" id="pwChk" class="pwChk"></td>
				</tr>
				<tr>
					<td colspan="2" id="pwChkMsg"></td>
				</tr>
				<tr>
					<th><label for="name">이름</label></th>
					<td><input type="text" name="name" id="name" class="name"></td>
				</tr>
				<tr>
					<th><label for="birth">생년월일</label></th>
					<td><input type="text" name="birth" id="datepicker" class="birth"></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="gender" value="m" checked="checked" id="m"><label for="m">남자</label>
						<input type="radio" name="gender" value="f" id="f"><label for="f">여자</label>
					</td>
				</tr>
				<tr>
					<th><label for="email">이메일</label></th>
					<td><input type="email" name="email" id="email" class="email"></td>
				</tr>
				<tr>
					<td colspan="2" id="emailChkMsg"></td>
				</tr>
				<tr>
					<th><label for="tel">전화번호</label></th>
					<td>
						<input type="tel" name="tel" id="tel" class="tel">
					</td>
				</tr>
								<tr>
					<th><label for="address">주소</label></th>
					<td>
						<input type="text" name="address" id="address" class="address">
					</td>
				</tr>
				<tr>
					<td colspan="2"> </td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="가입하기" class="joinBtn_style">
						<input type="reset" value="다시하기" class="joinBtn_style">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>