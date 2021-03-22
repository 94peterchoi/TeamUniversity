<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${user_path}script.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
 -->
<style>
* {
  box-sizing: border-box;
}

/* Create two unequal columns that floats next to each other */
.column {
  float: left;
  padding: 10px;
  height: 600px;  Should be removed. Only for demonstration */
  
}

.left {
  width: 80%;
}

.right {
  width: 20%;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

.row {
	margin: auto;
	width: 1300px;
}

.pay {
	background-color:#f2f2f2; 
	margin: 15px 15px;
	height: 25%;
	color: black;
	padding: 8px;
}

.pay:hover {
	border: 2px solid green;
}


.button {
  background-color: #ff013c;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

</style>

<title>구매 페이지</title>
</head>
<body>

<jsp:include page="navigation.jsp" />

  <br><br><br><br><br>

<div class="row">
  <div class="column left" style="background-color:white;">
    <h2>결제수단 선택</h2>
    <div id="pay1" class="pay" onclick="clickPay1()">
    	<h3>포인트 사용</h3>
    	<hr>
    	<p>고객님의 현재 보유포인트: ${point}</p>
    </div>
    
    <div id="pay2" class="pay" onclick="clickPay2()">
      	<h3>신용카드</h3>
    	<hr>
    	<p>미구현</p>
    </div>
    
  </div>
  
  
  <div class="column right" style="background-color:#f2f2f2;">
    <h2>게임정보</h2>
    <div><img src="/${gamelogo}" alt="로고이미지" style="width:200px; height:112px;"></div>
    <p>코드: ${code} <br>타이틀: ${title} <br> 퍼블리셔: ${publisher} <br> 가격: ${price}원 </p>
   	<button class="button" onclick="nowBuy(${price}, ${point}, ${code})" style="background-color: green;">구매하기</button>
  </div>
</div>


</body>
</html>
