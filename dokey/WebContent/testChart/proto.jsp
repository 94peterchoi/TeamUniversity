<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차트연동 연습중</title>
</head>
<body>

<table>
<tr>
	<th>날짜(스트링으로 나올까)</th>
	<th>버튼</th>
</tr>

<c:forEach var="list" items="${list}">
<tr>
	<td>${list}</td>
	<td><button onclick="test(${list});">버튼</button></td>
</tr>
</c:forEach>

</table>


<script>
function test(list) {
	console.log("다비");
	console.log(list);
}
</script>

</body>
</html>