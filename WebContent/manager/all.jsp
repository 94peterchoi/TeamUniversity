<!-- 관리자단 all.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>   

<c:set var="path" value="/dokey/"/>

    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
          integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
          crossorigin="anonymous"></script>

<title>게임조회 - 전체목록</title>

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
      <li><a href="/dokey/genre.m?genre=01">액션</a></li>
      <li><a href="/dokey/genre.m?genre=02">인디</a></li>
      <li><a href="/dokey/genre.m?genre=03">오픈월드</a></li>
      <li><a href="/dokey/genre.m?genre=04">RPG</a></li>
      <li><a href="/dokey/genre.m?genre=05">스포츠</a></li>
      <li><a href="/dokey/genre.m?genre=06">시뮬레이션</a></li>
      <li><a href="/dokey/genre.m?genre=07">레이싱</a></li>
      <li><a href="/dokey/genre.m?genre=08">전투</a></li>
      <li><a href="/dokey/genre.m?genre=09">로그라이트</a></li>
    </ul>
  </div>

<c:if test="${sessionScope.managerId != null}">
  <div class="right" style="background-color:black;">
  	<!-- 장르에 맞춰서 출력되도록 해야 함 -->
    <h2>전체게임 목록 </h2>
    <form name="insertGameForm" method="post" action="insertGame.m">
    	<input type="submit" name="insertGameInput" value="글쓰기">
    </form>

    <div class="wrap">
      <div class="row">

		<c:if test="${cnt > 0}">
			<c:forEach var="dto" items="${dtos}">
			
		        <div class="column">
		          <div class="content">
		          	<a href="detail.m?num=${dto.num}&pageNum=${pageNum}&number=${number+1}">
			          <img src="/${dto.thumbnail1}" alt="${dto.title}">
			        </a>
			            <p style="color:white;">${dto.title}<br>${dto.publisher}</p>
			            <p style="color:white;">${dto.price}￦</p>
			            <form name="delete" method="post" action="deleteAction.m">
			            	<input name="numValue" type="hidden" value="${dto.num}">
			            	<input name="codeValue" type="hidden" value="${dto.code}">
			            		
			            	<input type="submit" value="삭제">
			            </form>
			            
		          </div>
		        </div>
		        
		    </c:forEach>
		</c:if>
		
		
		<c:if test="${cnt == 0}">
			<p style="color:yellow; font-size: 20px;">게시글이 없습니다.</p> 
		</c:if>
		

      </div>
    </div>
    
    
    <!-- 페이지 컨트롤 -->
	<table style="width:500px; margin-left:auto; margin-right: auto; color:white; font-size:30px;">
		<tr>
			<th align="center">
				<c:if test="${cnt >0}">
					<c:if test="${startPage > pageBlock}">
	<!-- 				<a href="boardList.bo"> [◀◀] </a>  -->
						<a href="genre.m?genre=${genre}&pageNum=${startPage - pageBlock}">[◀]</a>
					</c:if>
					
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${i == currentPage}">
								<span><b> [${i}] </b></span>
							</c:if>
							
							<c:if test="${i != currentPage}">
								<a href="genre.m?genre=${genre}&pageNum=${i}"> [${i}] </a>
							</c:if>
	
					
					</c:forEach>
					
					<c:if test="${pageCount > endPage}">
						<a href="genre.m?genre=${genre}&pageNum=${startPage + pageBlock}"> [▶]</a>
					</c:if>
					
				</c:if>
			</th>
		</tr>
	</table>

  </div>
</c:if>
<c:if test="${sessionScope.managerId == null}">
	<p style="color:red">접근 권한이 없습니다.</p>
</c:if>

</div>




<jsp:include page="footer.jsp" />

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
    