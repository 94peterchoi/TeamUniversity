<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${user_path}script.js"></script>
<style>
body {
  font-family: Arial;
  font-size: 17px;
}


.row {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
  margin: 0 -16px;
}

.col-25 {
  -ms-flex: 25%; /* IE10 */
  flex: 25%;
}

.col-50 {
  -ms-flex: 50%; /* IE10 */
  flex: 50%;
}

.col-75 {
  -ms-flex: 75%; /* IE10 */
  flex: 75%;
}

.col-25,
.col-50,
.col-75 {
  padding: 0 16px;
}

.container {
  background-color: #f2f2f2;
  padding: 5px 20px 15px 20px;
  border: 1px solid lightgrey;
  border-radius: 3px;
}

input[type=text] {
  width: 100%;
  margin-bottom: 20px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

label {
  margin-bottom: 10px;
  display: block;
}

.icon-container {
  margin-bottom: 20px;
  padding: 7px 0;
  font-size: 24px;
}

.btn {
  background-color: #4CAF50;
  color: white;
  padding: 12px;
  margin: 10px 0;
  border: none;
  width: 100%;
  border-radius: 3px;
  cursor: pointer;
  font-size: 17px;
}

.btn:hover {
  background-color: #45a049;
}

a {
  color: #2196F3;
}

hr {
  border: 1px solid lightgrey;
}

span.price {
  float: right;
  color: grey;
}

/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
@media (max-width: 800px) {
  .row {
    flex-direction: column-reverse;
  }
  .col-25 {
    margin-bottom: 20px;
  }
}
</style>
</head>
<body>

<jsp:include page="navigation.jsp" />

  <br><br><br><br>


<h2 style="color:white">포인트 충전하기</h2>

<form name="addPointForm" method="post" action="addPointAction.do" onsubmit="return payCheck();">
	<div class="row">
	  <div class="col-75">
	    <div class="container">
	      
	      
	        <div class="row">
	
	          <div class="col-50">
	            <h3>신용카드결제</h3>
	            <label for="fname">결제 가능한 카드 종류</label>
	            <div class="icon-container">
	              <i class="fa fa-cc-visa" style="color:navy;"></i>
	              <i class="fa fa-cc-amex" style="color:blue;"></i>
	              <i class="fa fa-cc-mastercard" style="color:red;"></i>
	              <i class="fa fa-cc-discover" style="color:orange;"></i>
	            </div>
	            <label for="cname">명의자</label>
	            <input type="text" id="cardname" name="cardname" placeholder="홍길동">
	            <label for="ccnum">일련번호</label>
	            <input type="text" id="cardnum" name="cardnumber" placeholder="1111-2222-3333-4444">
	            <div class="row">
	              <div class="col-50">
	                <label for="exp">만료기간</label>
	                <input type="text" id="exp" name="exp" placeholder="01/22">
	              </div>
	              <div class="col-50">
	                <label for="cvc">CVC</label>
	                <input type="text" id="cvc" name="cvc" placeholder="352">
	              </div>
	            </div>
	          </div>
	          
	        </div>
	        <input type="submit" value="결제하기" class="btn" >
	    </div>
	  </div>
	  <div class="col-25">
	    <div class="container">
	      <h4>충전옵션 <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b>금액</b></span></h4>
	      <p><input type="radio" id="5000p" name="point" value="5000" onchange="changePoint(5000, 5000);">&nbsp;5000p<span class="price">5000원</span></p>
	      <p><input type="radio" id="10000p" name="point" value="10000" onchange="changePoint(10000, 10000);">&nbsp;10000p<span class="price">10000원</span></p>
	      <p><input type="radio" id="30000p" name="point" value="30000" onchange="changePoint(30000, 28500);">&nbsp;30000p<span class="price">28500원</span></p>
	      <p><input type="radio" id="50000p" name="point" value="50000" onchange="changePoint(50000, 46000);">&nbsp;50000p<span class="price">46000원</span></p>
	      <p><input type="radio" id="100000p" name="point" value="100000" onchange="changePoint(100000, 90000);">&nbsp;100000p<span class="price">90000원</span></p>
	
	      <hr>
	      <p>포인트충전 <span id="pointSpan" class="price" style="color:black"><b>0</b>p</span></p>
	      <p>결제금액 <span id="moneySpan" class="price" style="color:black"><b>0</b>원</span></p>
	    </div>
	  </div>
	</div>
</form>

</body>
</html>
