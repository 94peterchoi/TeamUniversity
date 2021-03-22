<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<html>
<head>


  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <!-- JS -->
  <script src="${u_path}script.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

  <!-- 구글폰트 -->
  <link href="https://fonts.googleapis.com/css?family=Stylish&display=swap" rel="stylesheet">

  <title>login</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      background-color: black;
      height: 100vh;
    }

    .text-info {
      color: white;
    }

    #login .container #login-row #login-column #login-box {
      margin-top: 120px;
      max-width: 600px;
      height: 350px;
      border: 1px solid #9C9C9C;
      background-color: #202020;
    }

    #login .container #login-row #login-column #login-box #login-form {
      padding: 20px;
    }

    #login .container #login-row #login-column #login-box #login-form #register-link {
      margin-top: -85px;
    }


    .form-control {
      display: block;
      width: 100%;
      padding: .375rem .75rem;
      font-size: 1rem;
      line-height: 1.5;
      color: white;
      background-color: #484848;
      background-clip: padding-box;
      border: none;
      border-radius: .25rem;
      transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out
    }
  </style>
</head>

<body>
  <div id="login">
    <h3 class="text-center text-white pt-5">로고 or 플젝명</h3>
    <div class="container">
      <div id="login-row" class="row justify-content-center align-items-center">
        <div id="login-column" class="col-md-6">
          <div id="login-box" class="col-md-12">
          
          
            <form id="login-form" name="loginForm" class="form" action="loginAction.do" method="post" onsubmit="return loginCheck();">
            <c:choose>
				<c:when test="${selectCnt == -1}"> <!-- 비밀번호 불일치 -->
					<h3 class="text-center text-secondary" style="background-color: yellow">비밀번호가 일치하지 않습니다.</h3>
				</c:when>
				<c:when test="${selectCnt == 0 }"> <!-- 존재하지 않는 아이디 -->
					<h3 class="text-center text-secondary" style="background-color: yellow">존재하지 않는 아이디입니다. </h3>
				</c:when>
			</c:choose>
			
              <h3 class="text-center text-secondary">Login</h3>
              <div class="form-group">
                <label for="id" class="text-white">id:</label><br>
                <input type="text" name="id" id="id" class="form-control">
              </div>
              <div class="form-group">
                <label for="password" class="text-white">Password:</label><br>
                <input type="password" name="pwd" id="pwd" class="form-control">
              </div>
              <div class="form-group">
                <label for="remember-me" class="text-info"><span>사용자 계정 저장</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                <input type="submit" name="submit" class="btn btn-info btn-md" value="로그인하기">
              </div>
              <div id="register-link" class="text-right">
                <a href="signin.do" class="text-info">회원가입하기</a>
                <br>
                <a href="#" class="text-info">비밀번호를 잊으셨나요?</a>
              </div>
              
            </form>
            
            
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html>
    