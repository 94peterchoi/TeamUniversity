<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  background-color: #1c1c1c !important;
}

* {
  box-sizing: border-box;
}

.navibar {
  overflow: hidden;
  background-color: #333;
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 2;
}

.navibar a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

#navright {
  float: right;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;

}


.navibar a:hover {
  background: #ddd;
  color: black;
}


/* Create a column layout with Flexbox */
.navrow {
  display: flex;
}

/* Left column (menu) */
.left {
  flex: 10%;
  padding: 15px 0;
}

.left h2 {
  padding-left: 8px;
}

/* Right column (page content) */
.right {
  flex: 90%;
  padding: 15px;
}

.right h2, p {
  color: gray;
}

/* Style the search box */
#mySearch {
  background-color: #333;
  width: 100%;
  font-size: 18px;
  padding: 11px;
  border: 1px solid #332;
}

/* Style the navigation menu inside the left column */
#myMenu {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

#myMenu li a {
  padding: 12px;
  text-decoration: none;
  color: gray;
  display: block
}

#myMenu li a:hover {
  background-color: #eee;
}

/* 계정 드롭다운 관련 CSS */
.dropbtn {
  background-color: #335;
  color: white;
  height: 50px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

.dropdown {
  position: relative;
  display: inline-block;
  z-index: 10;
}

.dropdown-content {
  display: none;
  position: absolute;
  right: 0;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #f1f1f1;}

.dropdown:hover .dropdown-content {
  display: block;
}

.dropdown:hover .dropbtn {
  background-color: #3e8e41;
}

</style>
<title>Insert title here</title>
</head>
<body>

 <div class="navibar">
	    <a href="#home">logo</a>
	    <a href="#home">홈화면</a>
	    <a href="store.m">스토어</a>
	    <a href="#news">새소식</a>
	    <a href="#contact">지원센터</a>
	    
	<c:if test="${sessionScope.managerId == null}">
		<a id="navright" href="login.do">로그인</a>
	</c:if>
</div>   
    
    
    
	<c:if test="${sessionScope.managerId != null}">
		<div class="dropdown" style="position: fixed; right:0;">
	   		<button class="dropbtn">${sessionScope.managerId}</button>
	   		<div class="dropdown-content">
			  <a href=".do든 .customer해서 고객아이디 넘겨줘야함">회원정보관리</a>
			  <a href="나머지도">판매 관련 통계</a>
			  <a href="나머지도">환불요청관리</a>
			  <a href="마찬가지">신고된 리뷰 확인</a>
			  <a href="#">슈퍼관리자</a>
			  <a href="#">관리자 pw 변경</a>
			  <a href="logout.m">로그아웃</a>
			</div>
	   	</div>
	</c:if>

 
 
</body>
</html>