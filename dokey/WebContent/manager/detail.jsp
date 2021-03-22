<!-- 관리자단 detail.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>


<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous">

  <title>${dto.title}</title>


  <style>
  @import url('https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'); /* 스타일 태그 맨 윗줄에 와야 적용됨 */

    /* =============이미지 슬라이드쇼 css 부분 ============ */
    img {
      vertical-align: middle;
    }

    /* Slideshow container */
    .slideshow-container {
      max-width: 800px;
      position: relative;
      margin: auto;
      z-index: 0;
    }

    /* Next & previous buttons */
    .prev,
    .next {
      cursor: pointer;
      position: absolute;
      top: 50%;
      width: auto;
      padding: 16px;
      margin-top: -22px;
      color: white;
      font-weight: bold;
      font-size: 18px;
      transition: 0.6s ease;
      border-radius: 0 3px 3px 0;
      user-select: none;
      background-color: gray;
    }

    /* Position the "next button" to the right */
    .next {
      right: 0;
      border-radius: 3px 0 0 3px;
    }

    /* On hover, add a black background color with a little bit see-through */
    .prev:hover,
    .next:hover {
      background-color: rgba(0, 0, 0, 0.8);
    }


    /* Number text (1/3 etc) */
    .numbertext {
      color: #f2f2f2;
      font-size: 12px;
      padding: 8px 12px;
      position: absolute;
      top: 0;
    }

    /* The dots/bullets/indicators */
    .dot {
      cursor: pointer;
      height: 15px;
      width: 15px;
      margin: 0 2px;
      background-color: #bbb;
      border-radius: 50%;
      display: inline-block;
      transition: background-color 0.6s ease;
    }

    .active,
    .dot:hover {
      background-color: #717171;
    }

    /* Fading animation 1000초 꼼수 */
    .fade {
      -webkit-animation-name: fade;
      -webkit-animation-duration: 1.5s;
      animation-name: fade;
      animation-duration: 1000s;
    }

    @-webkit-keyframes fade {
      from {
        opacity: 1
      }

      to {
        opacity: 1
      }
    }

    @keyframes fade {
      from {
        opacity: 1
      }

      to {
        opacity: 1
      }
    }

    /* On smaller screens, decrease text size */
    @media only screen and (max-width: 300px) {

      .prev,
      .next,
      .text {
        font-size: 11px
      }
    }

    /* ==================================== */


    /* Create three equal columns that floats next to each other */
    .column1 {
      float: left;
      width: 350px;
      padding: 10px;
      height: 300px;
      /* Should be removed. Only for demonstration */
      color: white;
    }

    .column2 {
      float: left;
      width: 550px;
      padding: 10px;
      height: 300px;
      /* Should be removed. Only for demonstration */
      color: white;
    }

    .column3 {
      float: left;
      width: 230px;
      padding: 10px;
      height: 300px;
      /* Should be removed. Only for demonstration */
      color: white;
    }

    .second-column1 {
      float: left;
      width: 300px;
      color: white;
    }

    .second-column2 {
      float: left;
      width: 830px;
      color: white;
    }

    .show-review {
      float: left;
      padding: 15px;
      width: 30%;
      height: 270px;
      color: white;
      border: 1px solid green;
      margin-right: 24px;
    }


    .wrap {
      display: flex;
      justify-content: center;
      /*가로에서 가운데에 요소(자식요소)를 배치하겠다*/
    }


    .button {
      background-color: #ff013c;
      /* red */
      border: none;
      color: white;
      padding: 15px 32px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
    }




  </style>
</head>

<body>

<jsp:include page="navigation.jsp" />



  <br><br><br><br><br><br><br>
  
<c:if test="${sessionScope.managerId != null}">
	<form name="updateGameForm" method="post" action="updateGame.m">
		<input style="margin-left:50px; font-size:30px" type="submit" value="수정하기">
		<input type="hidden" value="${dto.code}" name="code">
	</form>
</c:if>

<br><br>
  
  <p style="color:white; font-size:30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;조회수:${dto.game_view}</p>
  
  <div class="slideshow-container">
    <div class="mySlides fade">
      <div class="numbertext">1 / 4</div>
      <iframe width="100%" height="480px" src="${dto.trailer}">
      </iframe>
    </div>

    <div class="mySlides fade">
      <div class="numbertext">2 / 4</div>
      <img src="/${dto.thumbnail2}" style="width:100%; height:480px;">
    </div>

    <div class="mySlides fade">
      <div class="numbertext">3 / 4</div>
      <img src="/${dto.thumbnail3}" style="width:100%; height:480px;">
    </div>

    <div class="mySlides fade">
      <div class="numbertext">4 / 4</div>
      <img src="/${dto.thumbnail4}" style="width:100%; height:480px;">
    </div>


    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>

  </div>
  <br>
  <div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span>
    <span class="dot" onclick="currentSlide(2)"></span>
    <span class="dot" onclick="currentSlide(3)"></span>
    <span class="dot" onclick="currentSlide(4)"></span>
  </div>

  <br><br>


  <div class="wrap">

    <div class="row">
      <div class="column1" style="background-color:#1c1c1c;">
        <img src="/${dto.gamelogo}" style="width:300px">
      </div>
      <div class="column2" style="background-color:#1c1c1c;">
        <p style="color:white"> ${dto.description}
        </p>
      </div>
      <div class="column3" style="background-color:#1c1c1c;">
        <br>
        <a href="#" class="button">지금 구매하기</a>
        <br><br>
        <button class="button" style="background-color: green;">장바구니 담기</button>
      </div>
    </div>
  </div>


  <div class="wrap">
    <div class="row">
      <div class="second-column1" style="background-color:#1c1c1c;">
        ${dto.title} 정보
      </div>
      <div class="second-column2" style="background-color:#1c1c1c;">
        <table style="width:100%;" border="0">
          <tr>
            <td>개발사<br>${dto.developer}<br><br></td>
            <td>퍼블리셔<br>${dto.publisher}<br><br></td>
            <td>출시일<br>${dto.publishing_date}<br><br></td>
          </tr>
          <tr>
            <td>태그<br>${dto.genre}</td>
            <td>게임이용등급<br>${dto.rate}</td>
            <td>플랫폼<br>${dto.platform}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>

  <br>
  <br>

  <div class="wrap">
    <div class="row">
      <div class="second-column1" style="background-color:#1c1c1c;">
        평점
      </div>

      <div class="second-column2" style="background-color:#1c1c1c; ">
        <div class="show-review">
          <p>작성자: 맥도날드</p>
          <hr>
          <span>★★★★★</span>
          <!-- <div style="height: 150px"> -->
          <p style="height:100px;">우어어어어<br>나이스!!<br>굿굿<br>베리굿!<br></p>
          <a href="#">리뷰 자세히 보기</a>
        <!-- </div> -->


        </div>

        <div class="show-review">
          <p>작성자: TED</p>
          <hr>
          <span>★★★★★</span>
          <p>재밌는 게임 즐겁게 했습니다아아아아호호!</p>
        </div>

        <div class="show-review">
          <p>작성자: 김돌돌</p>
          <hr>
          <span>★</span>
          <p>실망실망 왕실망!! </p>
        </div>
        <a href="review.do?code=${dto.code}" style="font-size:30px; color:white;"> 모든 리뷰 보기 </a>

      </div>
    </div>
  </div>



<jsp:include page="footer.jsp" />

	
	
  <script>
    var slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
      showSlides(slideIndex += n);
    }

    function currentSlide(n) {
      showSlides(slideIndex = n);
    }

    function showSlides(n) {
      var i;
      var slides = document.getElementsByClassName("mySlides");
      var dots = document.getElementsByClassName("dot");
      if (n > slides.length) {
        slideIndex = 1
      }
      if (n < 1) {
        slideIndex = slides.length
      }
      for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
      }
      for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
      }

      slides[slideIndex - 1].style.display = "block";
      dots[slideIndex - 1].className += " active";
    }
  </script>




</body>

</html>
