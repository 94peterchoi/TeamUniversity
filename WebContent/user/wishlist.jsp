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
<title>고객 - 장바구니 조회</title>
<style>

body {
	color: white;
}

table {
	width: 70%;
}

table, th, td {
  border: 1px solid white;
  border-collapse: collapse;
}

table.center {
  margin-left: auto; 
  margin-right: auto;
}

</style>

</head>
<body>

<jsp:include page="navigation.jsp" />

  <br><br><br><br><br>

<table class="center">
		<tr>
			<th>로고 </th>
			<th>타이틀</th>
			<th>가격</th>
			<th>찜한날짜</th>
			<th>삭제하기</th>
		</tr>
	
	<c:forEach var="dto" items="${dtos}">
		<tr>
			<td>
			<a href="${dto.url}"><img src="/${dto.logo}" alt="게임이미지" style="width:30px; height:30px"></a>
			</td>
			<td>${dto.title}</td>
			<td>${dto.price}원</td>
			<td>${dto.pickdate}</td>
			<!-- <td><a href="deleteWishlist.do?code=${dto.gamecode}">삭제</a></td> -->
			<td><button onclick="deleteOne(${dto.gamecode})">삭제</button></td>
		</tr>
	</c:forEach>
	
</table>

</body>
</html>