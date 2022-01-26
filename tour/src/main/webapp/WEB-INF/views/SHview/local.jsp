<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 화면</title>
</head>
<style>
div {
	border: 1px solid black;
	text-align: center;
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
	<%@include file="../header2.jsp"%>

	<div class="content">
		<div class="content-item">
			<h3>지역 소개글</h3>
			<h4>지역 이미지</h4>
		</div>

		<div class="content-item">
			<h3>지역 관광지!!</h3>
			<div class="content-item-detail">
				<c:forEach var="tour" items="${localTourList}" begin="0" end="3">
						<div class="detail-items">
							<a href="detail?c_no=${tour.c_no}">${tour.c_title}</a>
						</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="content-item">
			<h3>지역 숙소!!</h3>
			<div class="content-item-detail">
				<c:forEach var="hotel" items="${localHotelList}" begin="0" end="3">
						<div class="detail-items">
							<a href="detail?c_no=${hotel.c_no}">${hotel.c_title}</a>
						</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="content-item">
			<h3>지역 맛집!!</h3>
			<div class="content-item-detail">
				<c:forEach var="food" items="${localFoodList}" begin="0" end="3">
						<div class="detail-items">
							<a href="detail?c_no=${food.c_no}">${food.c_title}</a>
						</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp"%>


</body>
</html>