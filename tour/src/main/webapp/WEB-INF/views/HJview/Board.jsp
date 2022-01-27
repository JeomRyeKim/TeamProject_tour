<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="boot.jsp" %>    
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function getRadioButton(radBut) {
	//alert('getRadioButton radBut-->'+radBut);
	if (radBut == 'a' ) {
		location.href = "http://localhost:9000/Board";
	} else if (radBut == '1') {
		location.href = "/Board?boardKindStr="+radBut;
	} else if (radBut == '2') {
		location.href = "/Board?boardKindStr="+radBut;
	} else if (radBut == '3') {
		location.href = "/Board?boardKindStr="+radBut;
	}
}

// function checkForm(){
// 	if(${sessionScope.sessionId==null}){ 
// 		$('#myModal').modal('show');
// 	}else{
// 		location.href="./writeForm?m_id=${sessionScope.sessionId}";
// 	}	
// }

function SearchText(search){
	var search = $('#search').val();
	alert("search : " + search);
	var items = $("#items option:selected").val();
	alert("items : " + items);
	location.href="/Board?"+items+"="+search;
}
</script>
</head>
<body>
<%@include file="../header1.jsp" %>
<%@include file="../header2.jsp" %>
<div class="container mt-5">
  <h2 id="board"><b>게시판</b></h2>
<pre>

</pre>
	<label><input type="radio" name="radBut" value="a"  id="all"    onclick="getRadioButton('a')">전체</label>
	<label><input type="radio" name="radBut" value="1"  id="free"   onclick="getRadioButton('1')">자유</label>
	<label><input type="radio" name="radBut" value="2"  id="review" onclick="getRadioButton('2')">후기</label>
	<label><input type="radio" name="radBut" value="3"  id="qna"    onclick="getRadioButton('3')">QnA</label>
	
	<input class="btn btn-outline-secondary btn-sm" style="float:right;" value="전체글 : ${total}" readonly>
	<c:set var="num" value="${pg.total-pg.start+1}"></c:set>	
<!-- <form action="/boardSearch" method="post"> -->
<table class="table table-hover">
    <thead>
      <tr>
        <th>카테고리</th>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>좋아요</th>
        <th>조회수</th>
      </tr>
    </thead>
	<tbody>
    <c:forEach var="board" items="${listBoard}">
	      <tr id="bb">
	      	  <c:if test="${board.b_kind eq 1}">
	        	<td>[자유]</td>
	          </c:if>
	      	  <c:if test="${board.b_kind eq 2}">
	        	<td>[후기]</td>
	          </c:if>
	      	  <c:if test="${board.b_kind eq 3}">
	        	<td>[QnA]</td>
	          </c:if>
	        <td>${board.b_no}</td>
	        <td><a href="detail?b_kind=${board.b_kind}&b_no=${board.b_no}">${board.b_title}</td>
	        <td>${board.m_nickname}</td>
	        <td>${board.b_date}</td>
	        <td>
	        	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
				  <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
				</svg> ${board.b_like_cnt}
	        </td>
	        <td>${board.b_hit}</td>
	      </tr>
    </c:forEach>
    </tbody>
	<c:set var="num" value="${num - 1}"></c:set>
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

<div align = "left">
  <table>
    <tr>
      <td width="100%" align="left">&nbsp;&nbsp;
        <select name="items" id="items" class="txt">
          <option value="b_title" <c:if test="${items=='b_title'}">selected</c:if>>제목에서</option>
          <option value="b_contents" <c:if test="${items=='b_contents'}">selected</c:if>>본문에서</option>
          <option value="m_nickname" <c:if test="${items=='m_nickname'}">selected</c:if>>글쓴이에서</option>
        </select>
          <input type="search" id="search" name="search" value="${search}">
          <input type="button" id="btnSearch" class="btn btn-outline-secondary btn-sm" value="검색" onclick="SearchText(${search})">
      </td>
         <a href="#" onclick="checkForm(); return false;" class="btn btn-outline-primary" style="float:right;" >&laquo;글쓰기</a>
    </tr>
  </table>
</div>

<!-- </form> -->
</div>
<pre>


</pre>
<%@include file="../footer.jsp" %>
<div class="modal" id="myModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">글쓰기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>로그인 해주세요</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">아니오</button>
        <button type="button" class="btn btn-outline-primary" onclick='javascript:location.href="./writeForm?m_id=${sessionScope.sessionId}"'>예</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>