<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>	
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="${u_path}script.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style>

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  background-color: black;
}

.container {
	width: 60% !important;
	margin-top : 2% !important;
	color: white !important;
}

.card {
 	background-color: #202020 !important;
}

.form-control {
	background-color: #484848 !important;
}

input {
	color: yellow !important;
	border: none !important;
}

.card-header {
	font-size: 30px;
}


</style>

<title>회원가입</title>
</head>
<body>

<div class="container">
<div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">회원가입</div>
                            <div class="card-body">

                                <form class="form-horizontal" name="signinForm" method="post" action="signinAction.do" onsubmit="return signinCheck();">
								  <input type="hidden" name="hiddenNum" id="hiddenNum" value="0" /> <!-- 이게 1이여야 가입하기 성공할 수 있음 -->
                                    <div class="form-group">
                                        <label for="name" class="cols-sm-2 control-label">Nickname
                                        <span id="nicknameError" style="color: red; display:none"> 닉네임은 2자~6자로 입력하세요</span></label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                <input type="text" class="form-control" name="nickname" id="nickname" placeholder="Enter your Name" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="cols-sm-2 control-label">Email<span id="emailError" style="color: red; display:none"> 이메일을 입력하세요</span></label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                                <input type="text" class="form-control" name="email" id="email" placeholder="Enter your Email" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="username" class="cols-sm-2 control-label">Id<span id="idError" style="color: red; display:none"> 아이디를 입력하세요</span></label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                                <input type="text" class="form-control" name="id" id="id" placeholder="Enter your Username" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="cols-sm-2 control-label">Password<span id="pwdError" style="color: red; display:none"> 비밀번호를 입력하세요</span></label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                                <input type="password" class="form-control" name="pwd" id="pwd" placeholder="Enter your Password" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="confirm" class="cols-sm-2 control-label">Confirm Password <span id="repwdError" style="color: red; display:none"> 비밀번호를 다시 확인해주세요.</span></label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                                <input type="password" class="form-control" name="repwd" id="repwd" placeholder="Confirm your Password" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="hp" class="cols-sm-2 control-label">hp<span id="hpError" style="color: red; display:none"> 010-xxxx-xxxx 형태로 입력하세요</span></label>
                                        <div class="cols-sm-10">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                                <input type="text" class="form-control" name="hp" id="hp" placeholder="Enter your Phone-number" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <button type="submit" class="btn btn-primary btn-lg btn-block login-button">가입하기</button>
                                    </div>
                                    <div class="login-register">
                                        <span>이미 계정이 있습니까? </span><a href="login.do">로그인하기</a>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
</div>
</body>
</html>
