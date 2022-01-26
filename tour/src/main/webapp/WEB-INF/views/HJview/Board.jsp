<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../header1.jsp" %>
<%@include file="../header2.jsp" %>
<div class="container mt-5">
  <h2 id="board"><b>게시판</b></h2>
<pre>

</pre>
	<c:set var="num" value="${pg.total-pg.start+1}"></c:set>	

<table class="table table-hover">
    <thead>
      <tr>
        <th>카테고리</th>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <c:forEach var="board" items="${listBoard}">
	<tbody>
	      <tr>
	      	<c:set>
	        	<td>${board.b_kind}</td>
	        </c:set>
	        <td>${board.b_no}</td>
	        <td><a href="detail?b_no=${board.b_no}">${board.b_title}</td>
	        <td>${board.m_nickname}</td>
	        <td>${board.b_date}</td>
	        <td>${board.b_hit}</td>
	      </tr>
    </tbody>
	<c:set var="num" value="${num - 1}"></c:set>
    </c:forEach>
</table>
<div align="center">
	<c:if test="${pg.startPage > pg.pageBlock}">
		<a href="Board?currentPage=${pg.startPage-pg.pageBlock}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
		<a href="Board?currentPage=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${pg.endPage < pg.totalPage}">
		<a href="Board?currentPage=${pg.startPage+pg.pageBlock}">[다음]</a>
	</c:if>
</div>
</div>
<pre>

</pre>
<%@include file="../footer.jsp" %>
</body>
</html>