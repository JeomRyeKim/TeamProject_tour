<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.container{
		display:flex;
		padding-bottom: 20px;
	}
	.form_container{
		margin:auto;
		border:none;
	}
</style>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
<script type="text/javascript">

//아이디 중복검사
function idCheck(){
	var m_id = $('#m_id').val();
	$.ajax({
		url:"<%=context%>/member/idCheck",
		data:{m_id : m_id}, //집어넣을 값지정
		dataType:'text', //받는 방법 설정 String이면 text 객체면 json으로
		success:function(data){
			const data1=$.trim(data);
			if(data1 == "1"){
				alert("이미 아이디가있습니다 다른아이디를 사용해주세요")
				$('#m_id').attr("autofocus",true);
				$('#m_idCheck').css("color","red");
				return false;
			}else{
				alert("사용하실수있는 아이디입니다")
				$('#m_idCheck').css("color","green");
				return true;
			}
		}
	});
	
}
function pwChange(){
	var m_id=$('#m_id').val();
	var m_password=$('#m_password').val();
	$.ajax({
		url:"<%=context%>/member/pwChange",
		data:{m_id : m_id,m_password:m_password}, //집어넣을 값지정
		dataType:'text', //받는 방법 설정 String이면 text 객체면 json으로
		success:function(data){
			alert("비밀번호 변경이 완료되었습니다");
		}
	});
}

function chkPW(){

	 var pw = $("#m_password").val();
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	 alert("비밀번호는 8~20자 영문,숫자,특수문자를 혼합하여 입력해주세요");
	 if(pw.length < 8 || pw.length > 20){
      $('#m_password').attr("autofocus",true);
	  alert("8자리 ~ 20자리 이내로 입력해주세요.");
	  return false;
	 }else if(pw.search(/\s/) != -1){
      $('#m_password').attr("autofocus",true);
	  alert("비밀번호는 공백 없이 입력해주세요.");
	  return false;
	 }else if(num < 0 || eng < 0 || spe < 0 ){
	  $('#m_password').attr("autofocus",true);
	  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
	  return false;
	 }else {
		console.log("비밀번호 검사완료"); 
		pwChange();
		return true;
	 }

}

</script>
</head>
<body>
	${member.m_id}
	<%@include file="../header1.jsp"%>
	<%@include file="./bootStrap.jsp"%>
	<h1>여기에 회원가입시 필요한 정보 및 input넣기</h1>
	<div class="container">
		<div class="form_container">
			<form action="/member/join" method="post" onsubmit="return form_submit()">
				<table>
					<tr>
						<td>아이디:</td>
						<td><input type="text" id="m_id" name="m_id" required="required" value="${member.m_id}" >
							<input type="button" id="m_idCheck" value="아이디중복검사"  onclick="idCheck()" >
							</td>
					</tr>
					<tr>
						<td>비밀번호:</td>
						<td><input type="password" id = "m_password" name="m_password" required="required" value="${member.m_password }">
							<input type="button" value="비밀번호 변경" onclick="chkPW()">
							<c:if test="${sessionScope.member.m_active_kind}==2">
							<input type="text" value="임시비밀번호를 변경해주세요">
							</c:if>
						</td>
						
					</tr>
					<tr>
						<td>닉네임:</td>
						<td><input type="text" name="m_nickname" required="required" value="${member.m_nickname }"></td>
					</tr>
					<tr>
						<td>생일:</td>
						<td><input type="date" name="m_birth" required="required" value="${member.m_birth }"></td>
					</tr>
					<tr>
						<td>이메일:</td>
						<td><input type="text" id="m_email" name="m_email" required="required" value=${member.m_email }>
						<input type="button" id="m_emailCk" value="이메일인증"  onclick="mailFormCheck()"></td>
					</tr>
					<tr>
						<td>인증번호확인:</td>
						<td><input type="text" id="m_email2" required="required">
						<input type="button" id="m_emailCk2" value="이메일인증확인"  onclick="emailCheck2()">
						<input type="hidden" id="m_email3" class="emailDoubleCk" >
						</td>
						
					</tr>
					<tr>
						<td>전화번호:</td>
						<td>
						<select id="m_phone1" name="m_phone1" required="required" >
							<option value="${member.m_phone1 }">010</option>
							<option value="010">011</option>
							<option value="010">017</option>
							<option value="010">019</option>
						</select>-
						<input type="text" name="m_phone2" required="required" value="${member.m_phone2}">-
						<input type="text" name="m_phone3" required="required" value="${member.m_phone3}">
						</td>
						
					</tr>
					<tr>
						<td>성별</td>
						<td><input type="text" name="m_gender" value="${member.m_gender }" required="required" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" id="submit" disabled="disabled" value="회원 정보수정"></td>
						<td colspan="2"><a href="#">회원탈퇴</a>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>