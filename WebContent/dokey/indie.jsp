<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- 부트스트랩 css
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous">

자바스크립트 css
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
          integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
          crossorigin="anonymous"></script> -->


  <title>게임조회 - 인디</title>


<style>


/* 오른쪽 박스 게임 카드뷰 부분 */
/* Center website 여기서 전체 감싸고 있는 div 가로 크기 조정할 수 있음*/
.wrap {
  width: 1100px;
}


.main {
  padding: 16px;
  margin-top: 30px;
  width: 1200px;
  /* Used in this example to enable scrolling */
}


h1 {
  font-size: 50px;
  word-break: break-all;
}

.row {
  margin: 10px;
}

.row,
.row > .column {
  padding: 8px;
}


/* .column img {
  float: left;
} */

.column {
  float: left;
  width: 25%;
}

/* Clear floats after rows */
.row:after {
  content: "";
  display: table;
  clear: both;
}

.content {
  padding: 10px;
}


.content img {
  width: 220px;
  height: 300px;
}



</style>
</head>
<body>

<jsp:include page="navigation.jsp" />


<br><br><br>
<p style="color: gray">찾아보기</p>

<div class="navrow">
  <div class="left" style="background-color:black;">
    <h2 style="color: gray;">Menu</h2>
    <input type="text" id="mySearch" onkeyup="myFunction()" placeholder="이름으로 검색" title="Type in a category">
    <ul id="myMenu">
      <li><a href="card-action.html">액션</a></li>
      <li><a href="#">인디</a></li>
      <li><a href="#">오픈월드</a></li>
      <li><a href="#">RPG</a></li>
      <li><a href="#">스포츠</a></li>
      <li><a href="#">시뮬레이션</a></li>
      <li><a href="#">레이싱</a></li>
      <li><a href="#">전투</a></li>
      <li><a href="#">로그라이트</a></li>
    </ul>
  </div>


  <div class="right" style="background-color:black;">
    <h2>인디게임 목록</h2>

    <div class="wrap">
      <div class="row">

        <div class="column">
          <div class="content">
            <img src="dokey/images/cookierun.png" alt="쿠키런">
            <p>쿠키런<br>데브시스터즈</p>
            <p>13000￦</p>
          </div>
        </div>

        <div class="column">
          <div class="content">
          <img src="dokey/images/cyberpunk.png" alt="사이버펑크">
            <p>사이버펑크<br>유비소프트</p>
            <p>24000￦</p>
          </div>
        </div>

        <div class="column">
          <div class="content">
            <img src="dokey/images/hades.jpg" alt="하데스">
            <p>하데스<br>슈퍼자이언트</p>
            <p>21000￦</p>
          </div>
        </div>

        <div class="column">
          <div class="content">
            <img src="dokey/images/undertale.png" alt="언더테일">
            <p>언더테일<br>토비 폭스</p>
            <p>10000￦</p>
          </div>
        </div>

        <div class="column">
          <div class="content">
            <img src="dokey/images/inside.png" alt="인사이드">
            <p>인사이드<br>플레이데드</p>
            <p>10000￦</p>
          </div>
        </div>

        <div class="column">
          <div class="content">
            <img src="dokey/images/pocketmon.jpg" alt="포켓몬골드">
            <p>포켓몬스터 골드<br>닌텐도</p>
            <p>4000￦</p>
          </div>
        </div>

        <div class="column">
          <div class="content">
            <img src="dokey/images/pocketmon.jpg" alt="쿠키런">
            <p>포켓몬스터 골드<br>닌텐도</p>
            <p>4000￦</p>
          </div>
        </div>

        <div class="column">
          <div class="content">
            <img src="dokey/images/pocketmon.jpg" alt="쿠키런">
            <p>포켓몬스터 골드<br>닌텐도</p>
            <p>4000￦</p>
          </div>
        </div>

        <div class="column">
          <div class="content">
            <img src="dokey/images/pocketmon.jpg" alt="쿠키런">
            <p>포켓몬스터 골드<br>닌텐도</p>
            <p>4000￦</p>
          </div>
        </div>


      </div>
    </div>

  </div>




</div>



<script>
function myFunction() {
  var input, filter, ul, li, a, i;
  input = document.getElementById("mySearch");
  filter = input.value.toUpperCase();
  ul = document.getElementById("myMenu");
  li = ul.getElementsByTagName("li");
  for (i = 0; i < li.length; i++) {
    a = li[i].getElementsByTagName("a")[0];
    if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
      li[i].style.display = "";
    } else {
      li[i].style.display = "none";
    }
  }
}
</script>

</body>
</html>
    