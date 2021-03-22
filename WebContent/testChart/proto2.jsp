<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %> 
<%@ page import="java.util.*" %> 
<%@ page import="java.sql.Timestamp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차트연동 연습중</title>
</head>
<body>

<% 
List<String> list = new ArrayList<String>();
List<Integer> copy = new ArrayList<Integer>();

list = (List<String>) request.getAttribute("list");
String tmp = "";
int num = 0;
int idx = 0;
for(int i=0; i<list.size(); i++) {
	
	if (tmp != list.get(i).substring(6)) {
		num = num + 1;
	} else {
		copy.add(idx, num);
		num = 0;
	}
	tmp = list.get(i).substring(6);
	idx = idx + 1;
}

for (int i=0; i<29; i++) {
	if (i == 4) {
	}
}



%>

<table>
<tr>
	<th>날짜(스트링으로 나올까)</th>
	<th>버튼</th>
</tr>

<tr>
	<td><%=list%></td>
	<td><button onclick="test();">버튼</button></td>
</tr>

</table>


<script>
function test() {
	console.log("다비");
	console.log(list);
}


var mylist = [];
var loop=0;

<%
for(int i=0; i<list.size(); i++){
%>
mylist[loop] = <%=list.get(i)%>; 

loop++;
<%
}
%>

for(i=0; i< mylist.length; i++) {
	  console.log("list: "+mylist[i]);
}


</script>

</body>
</html>