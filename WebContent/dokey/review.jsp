<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>   
    
<!DOCTYPE html>
  <html>

  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
      body {
        background-color: black;
        color: gray;
        font-size: 30px;
      }

      .all-review {

      }

      .review-container {
        background-color: #202020;
        width: 1000px;
        margin: 0 auto;
        border: 1px solid gray;
        margin-top: 15px;
      }

      .review-content {
        font-size: 18px;
        line-height: 24px;
        padding: 3px;
        word-break: break-all;
      }

      .writer-info {
        padding: 3px;
      }

      .comment {
        float: right;
      }
    </style>
  </head>

  <body>


    <div class="all-review">
    
	<c:forEach var="dto" items="${dtos}">
	    <div class="review-container">
	      <div>${dto.count_good}명이 이 평가가 유용하다고 함<br></div>
	      <div style="color: yellow">
	      	<c:if test="${dto.goodbad eq 'g'}">
		        <img src="dokey/images/icon_thumbsUp.png">
		        <c:choose>
		        <c:when test="${dto.starpoint == 5}">Good ★★★★★</c:when>
		        <c:when test="${dto.starpoint == 4}">Good ★★★★</c:when>
		        <c:when test="${dto.starpoint == 3}">Good ★★★</c:when>
		        <c:when test="${dto.starpoint == 2}">Good ★★</c:when>
		        <c:when test="${dto.starpoint == 1}">Good ★</c:when>
		        </c:choose>
			</c:if>
	      	<c:if test="${dto.goodbad eq 'b'}">
		        <img src="dokey/images/icon_thumbsDown_v6.png">
		        <c:choose>
		        <c:when test="${dto.starpoint == 5}">bad ★★★★★</c:when>
		        <c:when test="${dto.starpoint == 4}">bad ★★★★</c:when>
		        <c:when test="${dto.starpoint == 3}">bad ★★★</c:when>
		        <c:when test="${dto.starpoint == 2}">bad ★★</c:when>
		        <c:when test="${dto.starpoint == 1}">bad ★</c:when>
		        </c:choose>
			</c:if>


	      </div>
	      <div class="review-content">
	        ${dto.content}
	      </div>
	      <hr>
	      <div class="writer-info">
	        <img src="${dto.profile}" style="width:30px;height:30px">
	          ${dto.nickname} - ${dto.count_game}개 게임 보유 중
	          <span class="comment">대댓글보기</span>
	      </div>
	    </div>
	</c:forEach>
	

    </div>



  </body>

  </html>
