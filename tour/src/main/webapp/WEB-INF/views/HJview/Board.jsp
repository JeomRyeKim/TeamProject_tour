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
function SearchText(keyword){
	var searchType = $("#searchType option:selected").val();
	//alert("searchType->" + searchType);
	var keyword = $('#keyword').val();
	//alert("keyword->" + keyword);
	var b_kind = $('#b_kind').val();
	//alert("b_kind->" + b_kind);

	if(!window.location.search.includes('boardKindStr')){ // 전체 검색시
		//alert("!window.location.search.includes('boardKindStr')->전체 검색시");
		location.href = "/HJBoard?searchType=" + searchType + "&keyword="+keyword;
	}else if(window.location.search.includes('boardKindStr')) { // 유형별 검색시
		//alert("window.location.search.includes('boardKindStr')->유형별 검색시");
		location.href = "/HJBoard?boardKindStr=" + b_kind + "&searchType=" + searchType + "&keyword="+keyword;
		$('#b_kind').val("");
	}
	
}

function writeCheck(){
	var m_id = $('#id').val();
	var m_nickname = $('#nickname').val();
	var m_kind = $('#kind').val();
	var m_active_kind = $('#active_kind').val();
// 	alert("m_id->" + m_id);
// 	alert("m_nickname->" + m_nickname);
// 	alert("m_kind->" + m_kind);
// 	alert("m_active_kind->" + m_active_kind);
	
	if((m_id != null || m_id != "" || !StringUtils.isEmpty(m_id)) && m_active_kind == 1) {
		location.href="./HJWriteForm?m_id=" + m_id + "&m_nickname=" + m_nickname + "&m_kind=" + m_kind;
	} else {
		alert("활동중인 회원만 게시글 작성이 가능합니다");
// 		location.href="HJBoard?m_id=${sessionScope.m_id}"
	}
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
	<label><a class="btn btn-outline-secondary" name="radBut" value="a"  id="all"    onclick="location.href = '/HJBoard'">전체</a></label>
	<label><a class="btn btn-outline-secondary" name="radBut" value="1"  id="free"   onclick="location.href = '/HJBoard?boardKindStr=1'">자유</a></label>
	<label><a class="btn btn-outline-secondary" name="radBut" value="2"  id="review" onclick="location.href = '/HJBoard?boardKindStr=2'">후기</a></label>
	<label><a class="btn btn-outline-secondary" name="radBut" value="3"  id="qna"    onclick="location.href = '/HJBoard?boardKindStr=3'">QnA</a></label>
	
	<a class="btn btn-outline-secondary btn-sm" style="float:right;" readonly>전체글 : ${total}</a>
	<c:set var="num" value="${pg.total-pg.start+1}"></c:set>
		
	<br>m_id : ${member.m_id}
	<br>m_nickname : ${member.m_nickname}
	<br>m_kind : ${member.m_kind}
	<br>m_active_kind : ${member.m_active_kind}
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
    	<input type="hidden" id="id" value="${member.m_id}">
    	<input type="hidden" id="nickname" value="${member.m_nickname}">
    	<input type="hidden" id="kind" value="${member.m_kind}">
    	<input type="hidden" id="active_kind" value="${member.m_active_kind}">
<%--     	<input type="hidden" id="b_kind" value="${board.b_kind}"> --%>
<%--     	<input type="hidden" id="m_id" value="${board.m_id}"> --%>
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
	      	  <c:if test="${board.b_kind eq 4}">
	        	<td>[공지사항]</td>
	          </c:if>
	        <td>${board.b_no}</td>
        	<td><a href="HJBoardDetail?b_kind=${board.b_kind}&b_no=${board.b_no}&m_id=${member.m_id}">${board.b_title}</td>
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
		<a href="HJBoard?currentPage=${pg.startPage-pg.pageBlock}&m_id=${member.m_id}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
		<a href="HJBoard?currentPage=${i}&m_id=${member.m_id}">[${i}]</a>
	</c:forEach>
	<c:if test="${pg.endPage < pg.totalPage}">
		<a href="HJBoard?currentPage=${pg.startPage+pg.pageBlock}&m_id=${member.m_id}">[다음]</a>
	</c:if>
</div>

  <table>
    <tr>
      <td width="100%" align="left">
        <select name="searchType" id="searchType" class="txt" style="float: left;">
          <option value="b_title" <c:if test="${searchType=='b_title'}">selected</c:if>>제목에서</option>
          <option value="b_contents" <c:if test="${searchType=='b_contents'}">selected</c:if>>본문에서</option>
          <option value="m_nickname" <c:if test="${searchType=='m_nickname'}">selected</c:if>>글쓴이에서</option>
        </select>
          <input type="search" id="keyword" name="keyword" value="${keyword}">
          <input type="button" id="btnSearch" class="btn btn-outline-secondary btn-sm" value="검색" onclick="SearchText(${keyword})">
      </td>
         <input type="button" id="goWrite" onclick="writeCheck()" value="글쓰기&raquo;" class="btn btn-outline-primary" style="float: right;" >
    </tr>
  </table>
</div>

<pre>


</pre>
<%@include file="../footer.jsp" %>
</body>
</html>