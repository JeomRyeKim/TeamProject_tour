<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="boot.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../header1.jsp" %>
<%@include file="../header2.jsp" %>
<div class="container mt-5">
<pre>


</pre>
<form name="/HJBoardmodify" action="#" method="post">
<h5>
  <c:if test="${boardDetail.b_kind eq 1}">
 	<td>[자유] </td>
  </c:if>
  <c:if test="${boardDetail.b_kind eq 2}">
 	<td>[후기] </td>
  </c:if>
  <c:if test="${boardDetail.b_kind eq 3}">
 	<td>[QnA] </td>
  </c:if>
  <c:if test="${boardDetail.b_kind eq 4}">
 	<td>[공지사항] </td>
  </c:if>
 ${boardDetail.b_title}

</h5>
<p><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
  <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
</svg> ${boardDetail.m_nickname} | 

<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
  <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
  <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
</svg> ${boardDetail.b_date} | 

<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
  <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
  <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
</svg> ${boardDetail.b_hit} | 

<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
</svg> ${boardDetail.b_like_cnt}
<hr>
<p>boardDetail.b_filename : ${boardDetail.b_filename}
<%-- <p>boardDetail.savedName : ${boardDetail.savedName} --%>
<p><img src="" alt="게시판 이미지" width="500px" height="auto">
<p>${boardDetail.b_contents}
<p>sessionScope.m_kind 			: ${sessionScope.m_kind}
<p>sessionScope.m_nickname 		: ${sessionScope.m_nickname}
<p>sessionScope.m_active_kind	: ${sessionScope.m_active_kind}
<hr>
<p>sessionScope.b_kind 			: ${sessionScope.b_kind}
<p>sessionScope.b_no 			: ${sessionScope.b_no}
<p>sessionScope.m_id 			: ${sessionScope.m_id}
<hr>
<p>boardDetail.b_kind 			: ${boardDetail.b_kind}
<p>boardDetail.b_no 			: ${boardDetail.b_no}
<p>boardDetail.m_id 			: ${boardDetail.m_id}
<pre>

</pre>
</form>
</div>

<div class="container ">
  <c:choose>
    <c:when test="${sessionScope.m_id == boardDetail.m_id}"> <!-- 본인이 쓴 글일 때 -->
      <a type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#exampleModal">삭제</a>
	  <a type="submit" class="btn btn-outline-primary">수정</a>
  	  <a href="HJboardReply?b_kind=${boardDetail.b_kind}&b_no=${boardDetail.b_no}&m_id=${boardDetail.m_id}" class="btn btn-outline-success">답변</a>
    </c:when>
    <c:when test="${sessionScope.m_kind == 2}"> <!-- 관리자일 때 -->
      <a type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#exampleModal">삭제</a>
	  <a type="submit" class="btn btn-outline-primary">수정</a>
	  <a href="HJboardReply?b_kind=${boardDetail.b_kind}&b_no=${boardDetail.b_no}&m_id=${boardDetail.m_id}" class="btn btn-outline-success">답변</a>
    </c:when>
    <c:when test="${sessionScope.m_id == null}"></c:when>
    <c:when test="${sessionScope.m_id != boardDetail.m_id}"> <!-- 다른 사람 글을 볼 때 -->
	  <a href="HJboardReply_view?b_kind=${boardDetail.b_kind}&b_no=${boardDetail.b_no}&m_id=${boardDetail.m_id}" class="btn btn-outline-success">답변</a>
    </c:when>
    <c:otherwise></c:otherwise>
  </c:choose>
	  <a href="/HJBoard?b_kind=${board.b_kind}&b_no=${board.b_no}&m_id=${sessionScope.m_id}" class="btn btn-outline-secondary">목록</a>
</div>
<pre>

</pre>
<%@include file="../footer.jsp" %>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">게시글 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       ${boardDetail.b_no}번 글을 삭제하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">아니오</button>
        <button type="button" class="btn btn-outline-primary" onclick="#">예</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>