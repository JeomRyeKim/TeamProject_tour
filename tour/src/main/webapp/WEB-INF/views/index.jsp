<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<style>
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
	display: grid;
	grid-template-rows: 120px;
	grid-template-columns: 1fr 1fr 1fr 1fr;
	gap: 10px 10px;
	border: 1px solid black;
}
</style>
</head>
<body>
	<%@include file="./header1.jsp"%>
	<%@include file="./header2.jsp"%>
	<div class="jumbotron" style="height: 150px; padding: 20px 30px; text-align: center;">
		<h2>전국 여행 정보 !!</h2>
		<span> 검색창</span>
	</div>
	<div class="content">
		<div class="content-item">
			<h3>추천 관광지!!</h3>
			<div class="content-item-detail">
				<c:forEach var="tour" items="${tourList}" begin="0" end="3">
					<div class="detail-items">
						<a href="detail?c_no=${tour.c_no}">${tour.c_title}</a>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="content-item">
			<h3>추천 숙소!!</h3>
			<div class="content-item-detail">
				<c:forEach var="hotel" items="${hotelList}" begin="0" end="3">
					<div class="detail-items">
						<a href="detail?c_no=${hotel.c_no}">${hotel.c_title}</a>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="content-item">
			<h3>추천 맛집!!</h3>
			<div class="content-item-detail">
				<c:forEach var="food" items="${foodList}" begin="0" end="3">
					<div class="detail-items">
						<a href="detail?c_no=${food.c_no}">${food.c_title}</a>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="content-item">
			<h3>베스트 후기글</h3>
			<div class="content-item-detail">
				<div class="detail-items"></div>
				<div class="detail-items"></div>
				<div class="detail-items"></div>
				<div class="detail-items"></div>
			</div>
		</div>
	</div>
	<%@include file="./footer.jsp"%>
</body>
</html>