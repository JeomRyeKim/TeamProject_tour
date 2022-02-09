<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="boot.jsp" %> 
<%
	String context = request.getContextPath();
    System.out.println("context->"+context);
%>
<!DOCTYPE html>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<html> 
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#like_img{width: 45; height: auto;}
#dislike_img{width: 45; height: auto;}
</style>
<script>
function like_func(){
	// 로그인X
	if(${sessionScope.m_id == null}){ 
		alert("로그인 해주세요");
	}// 로그인O
	else { 
		var selB_like_check = $('#b_like_check').val();
		alert("selB_like_check->" + selB_like_check);
		var selB_kind = ${boardDetail.b_kind};
		var selB_no = ${boardDetail.b_no};
		var session_id = document.getElementById('session_id').value;
		alert("selB_kind->" + selB_kind + ", selB_no->" + selB_no);
		alert('session_id->' + session_id);
		
		alert("좋아요를 누르시겠습니까?");
		$.ajax(
				{
					url:"<%=context%>/HJBoardLike",
					data:{b_kind : selB_kind , b_no : selB_no, m_id : session_id, b_like_check : selB_like_check}, 
					dataType:'json', 
					success:function(data){
						alert("ajax 좋아요 y여부 data.BLikeChk->" + data.BLikeChk);
						alert("ajax 좋아요 n여부 data.BLikeChk_n->" + data.BLikeChk_n);
						alert("ajax 좋아요 insert 여부 data.insert_bl->" + data.insert_bl);
						alert("ajax 좋아요 b_like_cnt +1여부 data.like_b->" + data.like_b);
						alert("ajax 좋아요 update 여부 data.update_bl->" + data.update_bl);
						alert("ajax 좋아요 b_like_check 'y' -> 'n'으로 변경 여부 data.dislike_bl->" + data.dislike_bl);
						alert("ajax 좋아요b_like_cnt -1여부 data.dislike_b->" + data.dislike_b);
						alert("ajax 좋아요 y or n여부 data.b_like_check->" + data.b_like_check);
						alert("ajax 좋아요 총 개수 data.b_like_cnt->" + data.b_like_cnt);
						
						$("#b_like_check").val(data.b_like_check);
						$("#b_like_cnt").val(data.b_like_cnt);
						if(data.b_like_check == 'y'){
							document.getElementById("like_img").src = "./image/like/heart_like.png";
						}else{
							document.getElementById("like_img").src = "./image/like/heart_dislike.png";
						}
					},
					error:function(){
						alert("좋아요 error났습니다!");
					}
				}
		);
	}
}
</script>
<script>
$(document).ready(function(){
	$("#checkLock").change(function(){
		alert("lock 값을 변경합니다");
        if($("#checkLock").is(":checked")){
           $("#c_lock").val("y");
        }else{
           $("#c_lock").val("n");
        }
        var c_lock = $("#c_lock").val();
        alert("c_lock->" + c_lock);
    });
});
</script>
</head>
<body>
<%@include file="../header1.jsp" %>
<%@include file="../header2.jsp" %>
<div class="container mt-5">
<pre>


</pre>

<%-- <c:set var="present_id" value="${sessionScope.m_id}" /> --%>
<%-- <input type="hidden"  id="p_id" value="<c:out value="${present_id}"/>"> --%>
<h5>
  <c:if test="${boardDetail.b_lock == 'y'}">
  	🔒
  	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
	  <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/>
	</svg>
  </c:if>
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

<hr>
<p>boardDetail.b_filename : ${boardDetail.b_filename}
<%-- <p>boardDetail.savedName : ${boardDetail.savedName} --%>
<c:if test="${boardDetail.b_filename != null}">
	<p><img alt="게시판 이미지" src="resources/image/board/${boardDetail.b_filename}" width="500px" height="auto">
</c:if>
<p>${boardDetail.b_contents}
<p><input type="hidden" id="b_lock" value="${boardDetail.b_lock}">



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
<p>boardDetail.b_notice 		: ${boardDetail.b_notice}
<pre>

</pre>

</div>

<input type="hidden" id="session_id" value="${sessionScope.m_id}"> 
<input type="hidden" id="b_like_check" value="${b_like_check}">
<%-- <c:set var="session_id" value="${sessionScope.m_id}"></c:set> --%>
<!-- 좋아요 -->
<div class="container">
  <div class="row">
    <div class="col text-center">
      <c:choose>
        <c:when test='${b_like_check == "y"}'> <!-- 좋아요O 사람에게 보이는 이미지 : 회원 -->
	      <a href="javascript:like_func();" style="text-decoration:none" class="h3" data-value>
	        <img src='image/like/heart_like.png' id="like_img" class="likeImage">
	        <input type="text" id="b_like_cnt" style="border:none" value="${boardDetail.b_like_cnt}" readonly>
		  </a> 
	    </c:when>
	    <c:otherwise> <!-- 좋아요X 사람에게 보이는 이미지 : 회원 or 비회원 -->
	      <a href="javascript:like_func();" style="text-decoration:none" class="h3">
	        <img src='image/like/heart_dislike.png' id="like_img" class="likeImage">
	        <input type="text" id="b_like_cnt" style="border:none" value="${boardDetail.b_like_cnt}" readonly>
		  </a> 
	    </c:otherwise>
	  </c:choose> 
    </div>
  </div>
</div>
<pre>


</pre>
<div class="container">
	<div class="row"><hr></div>
</div>
<form action="HJBoardmodifyForm?m_id=${sessionScope.m_id}&b_kind=${boardDetail.b_kind}&b_no=${boardDetail.b_no}" 
	  method="post" enctype="multipart/form-data">
<input type="hidden" id="b_kind" name="b_kind" value="${boardDetail.b_kind}">
<input type="hidden" id="b_title" name="b_title" value="${boardDetail.b_title}">
<input type="hidden" id="m_nickname" name="m_nickname" value="${boardDetail.m_nickname}">
<input type="hidden" id="b_date" name="b_date" value="${boardDetail.b_date}">
<input type="hidden" id="b_hit" name="b_hit" value="${boardDetail.b_hit}">
<input type="hidden" id="b_filename" name="b_filename" value="${boardDetail.b_filename}">
<input type="hidden" id="b_contents" name="b_contents" value="${boardDetail.b_contents}">
<input type="hidden" id="b_lock" name="b_lock" value="${boardDetail.b_lock}">
<input type="hidden" id="b_notice" name="b_notice" value="${boardDetail.b_notice}">
<input type="hidden" id="b_Group" name="b_Group" value="${boardDetail.b_Group}">
<input type="hidden" id="b_Step" name="b_Step" value="${boardDetail.b_Step}">
<input type="hidden" id="b_Indent" name="b_Indent" value="${boardDetail.b_Indent}">
<div class="container ">
  <c:choose>
    <c:when test="${sessionScope.m_id == boardDetail.m_id}"> <!-- 본인이 쓴 글일 때 -->
      <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#myModal">삭제</button>
	  <input type="submit" value="수정" class="btn btn-outline-primary">
  	  <a href="HJboardReply_view?b_kind=${boardDetail.b_kind}&b_no=${boardDetail.b_no}&m_id=${boardDetail.m_id}&b_Group=${boardDetail.b_Group}&b_Step=${boardDetail.b_Step}&b_Indent=${boardDetail.b_Indent}"
  	   	 class="btn btn-outline-success">답변</a>
    </c:when>
    <c:when test="${sessionScope.m_kind == 2}"> <!-- 관리자일 때 -->
      <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#myModal">삭제</button>
	  <input type="submit" value="수정" class="btn btn-outline-primary">
	  <a href="HJboardReply_view?b_kind=${boardDetail.b_kind}&b_no=${boardDetail.b_no}&m_id=${boardDetail.m_id}&b_Group=${boardDetail.b_Group}&b_Step=${boardDetail.b_Step}&b_Indent=${boardDetail.b_Indent}"
	 	 class="btn btn-outline-success">답변</a>
    </c:when>
    <c:when test="${sessionScope.m_id == null}"></c:when>
    <c:when test="${sessionScope.m_id != boardDetail.m_id}"> <!-- 다른 사람 글을 볼 때 -->
	  <a href="HJboardReply_view?b_kind=${boardDetail.b_kind}&b_no=${boardDetail.b_no}&m_id=${boardDetail.m_id}&b_Group=${boardDetail.b_Group}&b_Step=${boardDetail.b_Step}&b_Indent=${boardDetail.b_Indent}"
	  	 class="btn btn-outline-success">답변</a>
    </c:when>
    <c:otherwise></c:otherwise>
  </c:choose>
  	  <a href="/HJBoard?m_id=${sessionScope.m_id}" class="btn btn-outline-secondary">목록</a>
</div>
</form>
<pre>

</pre>
<!-- 댓글 -->
<div class="container">
    <label for="content"><b>comment </b></label>
    <label><input type="checkbox" id="checkLock" value=""> 비밀글</label>
    <br>
    <form name="commentInsertForm">
        <div class="input-group">
           <input type="hidden" name="b_no" value="${boardDetail.b_no}"/>
           <input type="hidden" id="b_kind" name="b_kind" value="5"/>
           <input type="hidden" id="c_nickname" name="c_nickname" value="${param.m_nickname}">
           <input type="hidden" id="m_id" name="m_id" value="${sessionScope.m_id}">
		   <input type="hidden" id="c_lock" name="c_lock" value="n">
           <input type="text" class="form-control" id="c_contents" name="c_contents" placeholder="내용을 입력하세요.">
           <span class="input-group-btn">
                <button class="btn btn-outline-secondary" type="button" name="commentInsertBtn">등록</button>
           </span>
          </div>
    </form>
</div>

<input type="hidden" id="c_no" name="c_no" value=""/>
<div class="container">
    <div class="commentList"></div>
</div>
<pre>


</pre>
<%@include file="../footer.jsp" %>
<script>
$(document).ready(function(){
	var b_no = "${boardDetail.b_no}";
	alert("b_no->" + b_no);
	var c_nickname = $("#c_nickname").val();
	var c_lock = $("#c_lock").val();
	alert("c_nickname->" + c_nickname);
	alert("c_lock->" + c_lock);
    commentList(); //페이지 로딩시 댓글 목록 출력 
});


$('[name=commentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시 
    var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
    alert("insertData->" + insertData); // insertData->b_no=230&b_kind=5&c_nickname=zinoxxl&c_lock=n&c_contents=hi
    commentInsert(insertData); //Insert 함수호출(아래)
});

//댓글 등록
function commentInsert(insertData){
    $.ajax({
        url : '<%=context%>/HJCommentInsert',
        type : 'post',
        data : insertData,
        dataType:'json',
        success : function(data){
            if(data.b_no != null
            		) {
            	alert("댓글 insert 성공!");
            	alert("data.c_no->" + data.c_no);
            	alert("data.b_no->" + data.b_no);
            	alert("data.c_nickname->" + data.c_nickname);
            	alert("data.c_contents->" + data.c_contents);
            	alert("data.b_date->" + data.b_date);
            	alert("data.c_lock->" + data.c_lock);
            	
            	$("#c_no").val(data.c_no);
            	alert("c_no에 값 넣음");
                commentList(); //댓글 작성 후 댓글 목록 reload
                $('[name=c_contents]').val('');
            }
        },
        error:function(){
        	alert("댓글 insert 에러!");
        }
    });
}


//댓글 목록 
function commentList(){
	var b_no = "${boardDetail.b_no}";
	alert("b_no->" + b_no);
	var c_no = $("#c_no").val();
	alert("c_no->" + c_no);
	
    $.ajax({
        url : '<%=context%>/HJCommentList',
        type : 'get',
        data : {b_no : b_no},
//         dataType:'json',
        success : function(data){
        	var jsondata = JSON.stringify(data);
          	alert("댓글 리스트 가져오기 성공!");
        	alert("data.c_no->" + data.c_no);
        	alert("data.b_no->" + data.b_no);
        	alert("data.c_nickname->" + data.c_nickname);
        	alert("data.c_contents->" + data.c_contents);
        	alert("data.b_date->" + data.b_date);
        	alert("data.c_lock->" + data.c_lock);
            var a =''; 
//             $(data).each(function(){ 
//             	if(data.c_lock == 'n'){
            $.each(data, function(key, value){ 
                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '<div class="commentInfo'+value.c_no+'">'+' 작성자 : '+value.c_nickname;
                a += '<a onclick="commentUpdate('+value.c_no+',\''+value.c_contents+'\');"> 수정 </a>';
                a += '<a onclick="commentDelete('+value.c_no+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+value.c_no+'"> <p> 내용 : '+value.c_contents +'</p>';
                a += '</div></div>';
//             	}else{
// 	                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
// 	                a += '<div class="commentInfo'+this.c_no+'">'+' 작성자 : '+this.c_nickname;
// 	                a += '<a onclick="commentUpdate('+this.c_no+',\''+this.c_contents+'\');"> 수정 </a>';
// 	                a += '<a onclick="commentDelete('+this.c_no+');"> 삭제 </a> </div>';
// 	                a += '<div class="commentContent'+this.c_no+'"> <p> 내용 : '+this.c_contents +'</p>';
// 	                a += '</div></div>';
//             	}
            });
            
            $(".commentList").html(a);
        }
    });
}
















</script>

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">게시글 삭제</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        ${boardDetail.b_Group}번 글을 삭제하시겠습니까?
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">아니오</button>
        <button type="button" class="btn btn-outline-danger" onclick='location.href="HJBoardDelete?b_kind=${boardDetail.b_kind}&b_no=${boardDetail.b_no}&m_id=${sessionScope.m_id}"'>예</button>
      </div>

    </div>
  </div>
</div>
</body>
</html>