<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="boot.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.select_img img{margin: 20px 0;}
</style>
<script>
$(document).ready(function(){
	$("#b_lock").change(function(){         
	        if($("#b_lock").is(":checked")){
	           $("#b_lock").val("y");
	           var b_lock = $("#b_lock").val();
	           alert("b_lock->" + b_lock);
	        }
	    });

	});
</script>
</head>
<body>
<%@include file="../header1.jsp" %>
<%@include file="../header2.jsp" %>
<div class="container mt-5">
<h4><b>게시판 글쓰기</b></h4>
<hr>
<pre>


</pre>
<c:if test="${msg!=null}">${msg}</c:if>
<form id="writeFome" action="/HJWrite?m_id=${sessionScope.m_id}"
	  method="post" enctype="multipart/form-data">
<table>
	<br>param.m_nickname : ${param.m_nickname}
	<br>m_id : ${m_id}
    <input type="hidden" id="m_nickname" name="m_nickname" value="${sessionScope.m_nickname}">
    <input type="hidden" id="m_id" name="m_id" value="${sessionScope.m_id}">
    <input type="hidden" id="m_kind" name="m_kind" value="${sessionScope.m_kind}">
	<tr>
		<th class="row mt-1 mb-1" style="width:170px"><b align="center">카테고리</b></th>
		<td>
		    <select name="b_kind" class="btn btn-outline-light text-dark dropdown-toggle" data-bs-toggle="dropdown" required>
		      <option value="" selected disabled>선택</option>
		      <option class="dropdown-item" value="1">자유</option>
		      <option class="dropdown-item" value="2">후기</option>
		      <option class="dropdown-item" value="3">QnA</option>
		      <c:if test="${sessionScope.m_kind == 2}">
	      		<option class="dropdown-item" value="4">공지사항</option>
		      </c:if>
		    </select>
		    <input type="checkbox" name="b_lock" value="n"> 비밀글
	    </td>
	</tr>
    <tr>
    	<th class="row mt-1 mb-1" style="width:170px"><b align="center">제목</b></th>	 
		<td>
		<div class="form-floating-sm mb-2 mt-2">
	      <input type="text" class="form-control" id="b_title" name="b_title" placeholder="제목을 입력해주세요" name="b_title" required>
	    </div>
	    </td>
    </tr>
    <tr>
    	<th class="row mt-1 mb-1" style="width:170px"><b align="center">내용</b></th> 
		<td><textarea id="b_contents" name="b_contents" style="width: 1100px; height: 400px;" required></textarea></td>
	<tr>
	<tr>
    	<th class="row mt-1 mb-1" style="width:170px"><b align="center">첨부 파일</b></th>
    	<td>
			<input type="file" name="b_filename" id="b_filename">
			<div class="select_img"><img style="width: 500px; height: auto;" id="img" name="img"></div>
		</td>
	</tr>
</table>
<pre>

</pre>
	<input type="submit" value="입력" class="btn btn-outline-primary">
	<a href="/HJBoard?m_id=${sessionScope.m_id}" class="btn btn-outline-secondary">목록</a> 
</form>
</div>
<pre>

</pre>
<%@include file="../footer.jsp" %>
 <script>
  $("#b_filename").change(function(){
   if(this.files && this.files[0]) {
    var reader = new FileReader;
    reader.onload = function(data) {
     $(".select_img img").attr("src", data.target.result).width(500);        
    }
    reader.readAsDataURL(this.files[0]);
   }
  });
 </script>
</body>
</html>