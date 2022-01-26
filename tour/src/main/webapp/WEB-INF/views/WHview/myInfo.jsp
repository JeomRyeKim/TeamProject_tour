<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.container {
	display: flex;
	padding-bottom: 20px;
}

.form_container {
	margin: auto;
	border:none;
}
</style>
</head>
<body>
	<%@include file="../header1.jsp"%>
	<%@include file="./bootStrap.jsp"%>
	<h1>내정보 뜨는곳 수정업데이트 이곳에서</h1>
	<div class="container">
		<div class="form_container">
			<form action="memberUpdate">
				<h1>여기에서 비밀번호수정,회원탈퇴</h1>
				<table>
					<tr>
						<td>아이디:</td>
						<td><input type="text" name="" required="required"></td>
					</tr>
					<tr>
						<td>비밀번호:</td>
						<td><input type="text" name="" required="required"></td>
					</tr>
					<tr>
						<td>비밀번호 확인:</td>
						<td><input type="text" name="" required="required"></td>
					</tr>
					<tr>
						<td>생일:</td>
						<td><input type="text" name="" required="required"></td>
					</tr>
					<tr>
						<td>이메일:</td>
						<td><input type="text" name="" required="required"></td>
					</tr>
					<tr>
						<td>전화번호:</td>
						<td><input type="text" name="" required="required"></td>
					</tr>
					<tr>
						<td>???:</td>
						<td><input type="text" name="" required="required"></td>
					</tr>
					<tr>
						<td>???:</td>
						<td><input type="text" name="" required="required"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="회원정보 수정"></td>
					</tr>
				</table>
			</form>
			<a href="memberDelete">회원탈퇴</a>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>