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

<title>고객 - 구매이력 조회</title>

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
			<th>게임이름 </th>
			<th>구매날짜</th>
			<th>구매가격</th>
			<th>상태</th>
		</tr>
	
	<c:forEach var="dto" items="${dtos}">
		<tr>
			<td>${dto.title}</td>
			<td>${dto.pay_day}</td>
			<td>${dto.price}원</td>
		<c:if test="${dto.interval <= 7}">
			<td><button onclick="refundGame(${dto.gamecode}, ${dto.price})">환불가능</button></td>
		</c:if>
		<c:if test="${dto.interval > 7}">
			<td><button style="background-color:red">환불불가</button></td>
		</c:if>
		</tr>
	</c:forEach>
	
</table>

</body>
</html>