<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 화면</title>
<style>
div {
	border: 1px solid black;
	text-align: center;
	margin:10px 20px;
}

.items {
	text-align: center;
	vertical-align: middle;
}

.content-item {
	height: 200px;
	text-align: center;
	margin:20px 30px;
}
.content-item-detail{
	padding:0 10px;
	border:none;
	display: grid;
	grid-template-rows: 120px;
	grid-template-columns: 1fr 1fr 1fr 1fr ;
	gap: 10px 10px;
}
</style>
</head>
<body>
	<%@include file="../header1.jsp" %>
	<%@include file="../header2.jsp" %>

	<div class="jumbotron"
		style="height: 150px; padding: 20px 30px; text-align: center;">
		<h2>관광지(숙소,맛집) 상세 내용,정보,이미지</h2>
		
	</div>
	<div class="content">
		
		<div style="height:200px;">
			지도? 지역 설명? 넣고싶은거 넣는 자리
		</div>


		<div class="board" style="height:200px;">
			리뷰 게시판
		</div>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>