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
$(function(){
    
    $("input:button[name='button']").on('click',function(){
        var kind = $(this).val();
        $.ajax({
            
            url : "<%=context%>/BoardKind",
            type : "post",
            dataType:'json',
            cache : false,
            headers : {"cache-control":"no-cache","pragma":"no-cache"},
            data : {"kind":kind},
            success : function(data){
                console.log(data);
                $('body').html(data);
            },
            error : function(data){
                alert('error');
            }//error
        })//ajax
    });//button click
    
}); 
</script>
</head>
<body>
<%@include file="../header1.jsp" %>
<%@include file="../header2.jsp" %>
<div class="container mt-5">
  <h2 id="board"><b>게시판</b></h2>
<pre>

</pre>
	<input type="button" name="button" value="전체" class="btn btn-outline-secondary" id="all">
	<input type="button" name="button" value="자유" class="btn btn-outline-secondary" id="free">
	<input type="button" name="button" value="후기" class="btn btn-outline-secondary" id="review">
	<input type="button" name="button" value="QnA" class="btn btn-outline-secondary" id="qna">
	
	<input class="btn btn-outline-secondary btn-sm" style="float:right;" value="전체글 : ${total}">
	<c:set var="num" value="${pg.total-pg.start+1}"></c:set>	

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
	      <tr>
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
	        <td><a href="detail?b_no=${board.b_no}">${board.b_title}</td>
	        <td>${board.m_nickname}</td>
	        <td>${board.b_date}</td>
	        <td>♥${board.b_like_cnt}</td>
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
	<input type="button" value="글쓰기" href="#" class="btn btn-outline-secondary" style="float:right;">
</div>
<pre>


</pre>
<%@include file="../footer.jsp" %>
</body>
</html>