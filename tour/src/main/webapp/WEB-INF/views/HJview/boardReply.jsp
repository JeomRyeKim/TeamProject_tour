<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="boot.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(document).ready(function(){
	$("#b_lock").change(function(){
		alert("lock 값을 변경합니다");
        if($("#b_lock").is(":checked")){
           $("#b_lock").val("y");
        }else{
           $("#b_lock").val("n");
        }
        var b_lock = $("#b_lock").val();
        alert("b_lock->" + b_lock);
    });
});
</script>
</head>
<body>
<%@include file="../header1.jsp" %>
<%@include file="../header2.jsp" %>
<div class="container mt-5">
<h4><b>게시판 댓글입력</b></h4>
<hr>
<pre>


</pre>
<form>
<table>
    <input type="hidden" id="m_nickname" name="m_nickname" value="${boardDetail.m_nickname}">
    <input type="hidden" id="m_id" name="m_id" value="${boardDetail.m_id}">
    <input type="hidden" id="m_kind" name="m_kind" value="${boardDetail.m_kind}">
	<tr>
		<th class="row mt-1 mb-1" style="width:170px"><b align="center">카테고리</b></th>
		<c:if test="${boardDetail.m_kind == 1}">
			<td>
			    [자유]
			    <label><input type="checkbox" id="b_lock" name="b_lock" value="n"> 비밀글</label>
		    </td>
		</c:if>
		<c:if test="${boardDetail.m_kind == 2}">
			<td>
			    [후기]
			    <label><input type="checkbox" id="b_lock" name="b_lock" value="n"> 비밀글</label>
		    </td>
		</c:if>
		<c:if test="${boardDetail.m_kind == 3}">
			<td>
			    [QnA]
			    <label><input type="checkbox" id="b_lock" name="b_lock" value="n"> 비밀글</label>
		    </td>
		</c:if>
		<c:if test="${boardDetail.m_kind == 4}">
			<td>
			    [공지사항]
			    <label><input type="checkbox" id="b_lock" name="b_lock" value="n"> 비밀글</label>
		    </td>
		</c:if>
	</tr>
    <tr>
    	<th class="row mt-1 mb-1" style="width:170px"><b align="center">제목</b></th>	 
		<td>
		<div class="form-floating-sm mb-2 mt-2">
	      <input class="form-control" id="b_title" name="b_title" placeholder="제목을 입력해주세요" name="b_title" required value="${boardDetail.b_title}">
	    </div>
	    </td>
    </tr>
    <tr>
    	<th class="row mt-1 mb-1" style="width:170px"><b align="center">내용</b></th> 
		<td><textarea id="b_contents" name="b_contents" style="width: 1100px; height: 400px;" required>${boardDetail.b_contents}</textarea></td>
	<tr>
<!-- 	<tr> -->
<!--     	<th class="row mt-1 mb-1" style="width:170px"><b align="center">첨부 파일</b></th> -->
<!--     	<td> -->
<!-- 			<input type="file" name="b_filename" id="b_filename"> -->
<!-- 			<div class="select_img"><img style="width: 500px; height: auto;" id="img" name="img"></div> -->
<!-- 		</td> -->
<!-- 	</tr> -->
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
</body>
</html>