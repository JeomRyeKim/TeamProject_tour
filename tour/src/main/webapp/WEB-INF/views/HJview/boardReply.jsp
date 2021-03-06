<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="boot.jsp" %>
<!DOCTYPE html>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(document).ready(function(){
 	$("#checkLock").change(function(){
 		alert("lock 값을 변경합니다");
         if($("#checkLock").is(":checked")){
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
<h4><b>게시판 답변글 입력</b></h4>
<hr>
<pre>


</pre>
<c:if test="${msg!=null}">${msg}</c:if>
<form action="HJboardReply" method="post" enctype="multipart/form-data">
    <input type="hidden" id="m_id" name="m_id" value="${boardDetail.m_id}">
    <input type="hidden" id="m_nickname" name="m_nickname" value="${boardDetail.m_nickname}">
    <input type="hidden" id="b_kind" name="b_kind" value="${boardDetail.b_kind}">
    <input type="hidden" id="b_no" name="b_no" value="${boardDetail.b_no}">
    <input type="hidden" id="b_lock" name="b_lock" value="${boardDetail.b_lock}">
    <input type="hidden" id="b_notice" name="b_notice" value="${boardDetail.b_notice}">
    <input type="hidden" id="b_Group" name="b_Group" value="${boardDetail.b_Group}">
    <input type="hidden" id="b_Step" name="b_Step" value="${boardDetail.b_Step}">
    <input type="hidden" id="b_Indent" name="b_Indent" value="${boardDetail.b_Indent}">
<table>
	<tr>
		<th class="row mt-1 mb-1" style="width:170px"><b align="center">카테고리</b></th>
		<!-- 비밀글 O -> checked O -->
		<c:if test="${boardDetail.b_kind == 1 && boardDetail.b_lock == 'y'}">
			<td>
			    [자유]
			    <label><input type="checkbox" id="checkLock" value="" checked> 비밀글</label>
		    </td>
		</c:if>
		<c:if test="${boardDetail.b_kind == 2 && boardDetail.b_lock == 'y'}">
			<td>
			    [후기]
			    <label><input type="checkbox" id="checkLock" value="" checked> 비밀글</label>
		    </td>
		</c:if>
		<c:if test="${boardDetail.b_kind == 3 && boardDetail.b_lock == 'y'}">
			<td>
			    [QnA]
			    <label><input type="checkbox" id="checkLock" value="" checked> 비밀글</label>
		    </td>
		</c:if>
		<c:if test="${boardDetail.b_kind == 4 && boardDetail.b_lock == 'y'}">
			<td>
			    [공지사항]
			    <label><input type="checkbox" id="checkLock" value="" checked> 비밀글</label>
		    </td>
		</c:if>
		<!-- 비밀글 X -> checked X -->
		<c:if test="${boardDetail.b_kind == 1 && boardDetail.b_lock == 'n'}">
			<td>
			    [자유]
			    <label><input type="checkbox" id="checkLock" value=""> 비밀글</label>
		    </td>
		</c:if>
		<c:if test="${boardDetail.b_kind == 2 && boardDetail.b_lock == 'n'}">
			<td>
			    [후기]
			    <label><input type="checkbox" id="checkLock" value=""> 비밀글</label>
		    </td>
		</c:if>
		<c:if test="${boardDetail.b_kind == 3 && boardDetail.b_lock == 'n'}">
			<td>
			    [QnA]
			    <label><input type="checkbox" id="checkLock" value=""> 비밀글</label>
		    </td>
		</c:if>
		<c:if test="${boardDetail.b_kind == 4 && boardDetail.b_lock == 'n'}">
			<td>
			    [공지사항]
			    <label><input type="checkbox" id="checkLock" value=""> 비밀글</label>
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
	<tr>
    	<th class="row mt-1 mb-1" style="width:170px"><b align="center">첨부 파일</b></th>
    	<td>
			<input type="file" id="filename" name="filename">
			<span id="imgdel"><a href="#" id="preview-de" class="btn btn-outline-success">삭제</a></span>
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
  $("#filename").change(function(){
   if(this.files && this.files[0]) {
    var reader = new FileReader;
    if(document.getElementById("img")){
    	alert("img있음!");
    }else{
    	alert("img없음!");
	    var str = '<img style="width: 500px; height: auto;" id="img" name="img">'
	    alert("str->" + str);
    	$(".select_img").append(str);
    }
    
    reader.onload = function(data) {
     $(".select_img img").attr("src", data.target.result).width(500);        
    }
    reader.readAsDataURL(this.files[0]);
   }
  });
  
  $('#imgdel').on('click', '#preview-de', function () {
	  alert("사진 삭제합니다");	  
	  $(".select_img").empty()
	  $("#filename").val("");
  });
 </script>
</body>
</html>