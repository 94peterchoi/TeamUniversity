<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="setting.jsp" %>

<script src="${u_path}script.js"></script>

<!-- <body onload="confirmIdFocus();"> -->
<body>

<!-- id 중복 -->
<c:if test="${selectCnt == 1}">
    <table>
       <tr>
          <td colspan="2">
             <span>${id}</span>는 이미 사용중인 아이디입니다.
          </td>         
       </tr>
    </table>
</c:if>
<c:if test="${selectCnt != 1}">
    <table>
       <tr>
          <td colspan="2">
             <span>${id}</span>는 사용가능한 아이디입니다.
          </td>   
          <td>
          	 <input type="button" value="확인" onclick="setNum();">
          </td>      
       </tr>
    </table>

</c:if>




</body>
</html>