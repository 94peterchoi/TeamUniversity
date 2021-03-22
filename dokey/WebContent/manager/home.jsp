<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
    

<html>
<body>

<!-- 모르겠다 일단 필요없는 거 같아 지금은 
<c:if test="${selectCnt == 7}">
	관리자페이징
</c:if>

<c:if test="${selectCnt != 7}">
	접근할 수 없습니다.
</c:if>
!-->

<c:if test="${deleteCnt == 1}">
<jsp:include page="navigation.jsp" />
<p style="color:white; font-size:150px;">관리자 홈화면입니다. 게임이 성공적으로 삭제되었습니다</p>
<jsp:include page="footer.jsp" />
</c:if>

<c:if test="${deleteCnt != 1}">
<jsp:include page="navigation.jsp" />
<p style="color:white; font-size:150px;">관리자 홈화면입니다</p>
<jsp:include page="footer.jsp" />
</c:if>






</body>
</html>