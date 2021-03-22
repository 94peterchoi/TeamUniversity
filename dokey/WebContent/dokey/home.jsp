<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<html>
<body>

<jsp:include page="navigation.jsp" />


<p style="color:white; font-size:150px;">홈화면입니다</p>

<c:if test="${sessionScope.userId == null}">
	<p style="color: yellow;">
		<c:choose>
			<c:when test="${selectCnt == -1}">
				비밀번호 불일치. 
			</c:when>
			<c:when test="${selectCnt == 0}">
				존재하지 않는 아이디. 
			</c:when>
		</c:choose>
	</p>
</c:if>


<c:if test="${sessionScope.userId != null}">
	<p>
	${sessionScope.userId}로그인 완료
	</p>
</c:if>


<jsp:include page="footer.jsp" />


</body>
</html>
    