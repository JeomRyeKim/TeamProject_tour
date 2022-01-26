<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
div {
	border: 1px solid black;
	text-align: center;
}

.container {
	display: grid;
	grid-template-rows: 60px;
	grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
}

.items {
	text-align: center;
	vertical-align: middle;
}

.content-item {
	height: 200px;
	text-align: center;
	margin: 20px 30px;
}

.content-item-detail {
	padding: 0 10px;
	border: none;
	display: grid;
	grid-template-rows: 120px;
	grid-template-columns: 1fr 1fr 1fr 1fr;
	gap: 10px 10px;
}
</style>
<body>
	<%@include file="../header1.jsp"%>
	<%@include file="./bootStrap.jsp"%>
	<div class="content">
		<h3>찜 목록</h3>
		<div class="content-item">
			<div class="content-item-detail">
				<div class="detail-items">
					<a href="detail">찜한거</a>
				</div>
				<div class="detail-items">
					<a href="detail">찜한거</a>
				</div>
				<div class="detail-items">
					<a href="detail">찜한거</a>
				</div>
				<div class="detail-items">
					<a href="detail">찜한거</a>
				</div>
			</div>
		</div>
	</div>
	<div>
		<a href="">나의문의사항</a> <a href="">내가쓴글</a> <a href="myPlanner">마이플래너
			작성하러가기</a>
	</div>
	<%@include file="../footer.jsp"%>
</body>
</html>