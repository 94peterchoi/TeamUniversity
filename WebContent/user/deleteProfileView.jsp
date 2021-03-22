<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>회원탈퇴 - 사유입력</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="${user_path}script.js"></script>

<style>
.container {
	color: white;
	font-size: 25px;
}

</style>
  
  
</head>
<body>
<jsp:include page="navigation.jsp" />
<br><br><br><br>


<div class="container">
	<form id="delete-form" name="delete-form" action="deleteProfileAction.do" method="post" onsubmit="return reCheck();">
	 <fieldset>
	 <legend>회원탈퇴 사유를 입력해주세요</legend>
		  <div class="radio">
		    <label><input type="radio" id="reason" name="reason" value="빈약한 컨텐츠" checked>빈약한 컨텐츠</label>
		  </div>
		  <div class="radio">
		    <label><input type="radio" id="reason" name="reason" value="재가입 목적">재가입 목적</label>
		  </div>
		  <div class="radio">
		    <label><input type="radio" id="reason" name="reason" value="유저 편의성 부족">유저 편의성 부족</label>
		  </div>
		  <br>
		  <textarea id="suggestion" name="suggestion" rows="5" cols="50" placeholder="그밖의 건의사항이 있다면 말씀해주세요" style="color:black;"></textarea>
		  <div id="test_cnt">(0 / 100)</div>
		  <br>
		  <input type="submit" value="회원탈퇴하기" style="color: black">
	 </fieldset>
	</form>
</div>


</body>
</html>