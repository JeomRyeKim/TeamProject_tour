<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div id="menu1">
		<nav class="navbar navbar-expand-sm bg-light navbar-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="/">Logo</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-end"
					id="collapsibleNavbar">
					<ul class="navbar-nav">
						<%-- 	<c:choose> --%>
						<%--   	  <c:when test="${empty sessionId}"> --%>
						<li class="nav-item"><a class="nav-ling" >${sessionScope.m_id}</a>
						<li class="nav-item"><a class="nav-link" href="memberLogin">로그인</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="memberJoin">회원가입</a></li>
						<li class="nav-item">
							<%--       </c:when> --%> <%-- 	  <c:otherwise>    --%>
						<li class="nav-item"><a class="nav-link" href="memberLogout">로그아웃</a></li>
						<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" role="button"
							data-bs-toggle="dropdown">마이페이지</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="myInfo">나의 정보</a></li>
								<li><a class="dropdown-item" href="myWishList">나의 찜 목록</a></li>
							</ul></li>
						<li class="nav-item"><a class="nav-link" href="myPlanner">플래너 작성</a>
						</li>
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${not empty sessionScope.m_id}"> --%>
								<li class="nav-item"><a class="nav-link" href="HJBoard?m_id=${sessionScope.m_id}">게시판</a></li>
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<!-- 								<li class="nav-item"><a class="nav-link" href="/HJBoard">게시판</a></li> -->
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
					</ul>
				</div>
			</div>
		</nav>
		</div>
</body>
</html>