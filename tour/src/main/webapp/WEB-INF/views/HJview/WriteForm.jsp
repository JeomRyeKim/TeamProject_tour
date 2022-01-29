<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="boot.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <!-- Bootstrap 5 CDN Link -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Summernote CSS - CDN Link -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <!-- //Summernote CSS - CDN Link -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Summernote JS - CDN Link -->
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    <script>
    $(document).ready(function () {
        $('#summernote').summernote({
            placeholder: '내용을 작성하세요',
            height: 400,
            maxHeight: 400
        });
    });
    </script>
    <!-- //Summernote JS - CDN Link -->
</head>
<body>
<%@include file="../header1.jsp" %>
<%@include file="../header2.jsp" %>
<div class="container mt-5">
<h4><b>게시판 글쓰기</b></h4>
<hr>
<pre>


</pre>
<form action="write" method="post" name="frm" enctype="multipart/form-data">
<table>
	<tr>
		<th class="row mt-1 mb-1" style="width:170px"><b align="center">옵션</b></th><td><input type="checkbox" name="b_lock" value="bLock"> 비밀글</td>
	</tr>
	<tr>
		<th class="row mt-1 mb-1" style="width:170px"><b align="center">카테고리</b></th>
		<td>
		    <select name="b_kind" class="btn btn-outline-light text-dark dropdown-toggle" data-bs-toggle="dropdown" required>
		      <option value="" selected disabled>선택</option>
		      <option class="dropdown-item" value="1">자유</option>
		      <option class="dropdown-item" value="2">후기</option>
		      <option class="dropdown-item" value="3">QnA</option>
		      <option class="dropdown-item" value="4">공지사항</option>
		    </select>
	    </td>
	</tr>
	<tr>
		<th class="row mt-1 mb-1" style="width:170px"><b align="center">닉네임</b></th>
		<td>	 
		<div class="form-floating-sm mb-2 mt-2">
	      <input type="text" class="form-control" id="m_nickname" name="m_nickname" placeholder="닉네임을 입력해주세요" name="m_nickname">
	    </div>
	    </td>
    </tr>
    <tr>
    	<th class="row mt-1 mb-1" style="width:170px"><b align="center">제목</b></th>	 
		<td>
		<div class="form-floating-sm mb-2 mt-2">
	      <input type="text" class="form-control" id="b_title" name="b_title" placeholder="제목을 입력해주세요" name="b_title">
	    </div>
	    </td>
    </tr>
    <tr>
    	<th class="row mt-1 mb-1" style="width:170px"><b align="center">내용</b></th> 
		<td><textarea id="summernote" name="b_contents"></textarea></td>
	<tr>
</table>
<pre>

</pre>
	<input type="submit" value="입력" class="btn btn-outline-primary">
	<a href="/HJBoard" class="btn btn-outline-secondary">목록보기</a> 
</form>
</div>
<pre>

</pre>
<%@include file="../footer.jsp" %>
</body>
</html>