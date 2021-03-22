<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous">
        
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	<script src="${m_path}script.js"></script>

  <title>스토어에 게임 올리기 (글쓰기)</title>


  <style>
  @import url('https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'); /* 스타일 태그 맨 윗줄에 와야 적용됨 */

    body {
      margin: 0;
      font-family: Arial, Helvetica, sans-serif;
      background-color: #385723 !important;
    }

    * {
      box-sizing: border-box;
    }



    /* Create three equal columns that floats next to each other */
    .column1 {
      float: left;
      width: 350px;
      padding: 10px;
      height: 300px;
      /* Should be removed. Only for demonstration */
      color: white;
    }

    .column2 {
      float: left;
      width: 550px;
      padding: 10px;
      height: 300px;
      /* Should be removed. Only for demonstration */
      color: white;
    }

    .column3 {
      float: left;
      width: 230px;
      padding: 10px;
      height: 300px;
      /* Should be removed. Only for demonstration */
      color: white;
    }

    .second-column1 {
      float: left;
      width: 300px;
      color: white;
    }

    .second-column2 {
      float: left;
      width: 830px;
      color: white;
    }

    .show-review {
      float: left;
      padding: 15px;
      width: 30%;
      height: 270px;
      color: white;
      border: 1px solid green;
      margin-right: 24px;
    }


    .wrap {
      display: flex;
      justify-content: center;
      /*가로에서 가운데에 요소(자식요소)를 배치하겠다*/
    }


    .button {
      background-color: #ff013c;
      /* red */
      border: none;
      color: white;
      padding: 15px 32px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
    }

	label {
		color:white;
	}


  </style>
</head>

<body>

<jsp:include page="navigation.jsp" />


<c:if test="${sessionScope.managerId != null}">
<form name="insertGameForm" method="post" enctype="multipart/form-data" action="insertGameAction.m">
 <div style="color:white">    
      <br>
      <br>
      <br>
      <br>
      <br>
      
<table align="center">

	<tr>
		<td colspan="2"><h2>관리자용 스토어 게임 등록<br><br></h2></td>
	</tr>
	
	<tr>
		<td><label for="code">게임코드</label><br><br></td>
	    <td><input name="code" type="text"><br><br></td>
    </tr>
    
    <tr>
   	  	<td><label for="price">정상가</label><br><br></td>
	    <td><input name="price" type="text"><br><br></td>
    </tr> 	
    
	<tr>
      <td><label for="price2">할인가(할인하지 않는다면 0)<br><br></label></td>
      <td><input name="price2" type="text"><br><br></td>
    </tr>
     
      
	<tr>
      <td><label for="trailer">트레일러<br><br></label></td>
      <td><input name="trailer" type="text"><br><br></td>
    </tr>

	<tr>
      <td><label for="thumbnail1">썸네일1(스토어 대표사진)<br><br></label></td>
      <td><input name="thumbnail1" type="file"><br><br></td>
    </tr>
      
	<tr>
      <td><label for="thumbnail2">썸네일2(슬라이드 이미지)<br><br></label></td>
      <td><input name="thumbnail2" type="file"><br><br></td>
    </tr>
	
	<tr>
      <td><label for="thumbnail3">썸네일3(슬라이드 이미지)<br><br></label></td>
      <td><input name="thumbnail3" type="file"><br><br></td>
    </tr>
	  
	<tr>
      <td><label for="thumbnail4">썸네일4(슬라이드 이미지)</label><br><br></td>
      <td><input name="thumbnail4" type="file"><br><br></td>
    </tr>
	  
	<tr>
      <td><label for="logo">게임 로고</label><br><br></td>
      <td><input name="logo" type="file"><br><br></td>
    </tr>
	  
</table> 

  <br><br>
  


</div>

  <div class="wrap">

    <div class="row">
      <div class="column1" style="background-color:#1c1c1c;">
        <img src="" alt="로고 들어갈 부분" style="width:300px">
        
      </div>
      <div class="column2" style="background-color:#1c1c1c;">
      	<textarea rows="10" cols="60" name="description" placeholder="게임 설명 부분"></textarea>
      </div>
      
      <div class="column3" style="background-color:#1c1c1c;">
        <br>
        <a href="#" class="button">구매버튼 자리</a>
        <br><br>
        <button class="button" style="background-color: green;">장바구니 자리</button>
      </div>
    </div>
  </div>


  <div class="wrap">
    <div class="row">
      <div class="second-column1" style="background-color:#1c1c1c;">
      <label for="title">게임제목</label><br>
      	<input name="title" type="text" size="12">
      </div>
      <div class="second-column2" style="background-color:#1c1c1c;">
        <table style="width:100%;" border="0">
          <tr>
            <td>개발사<br><input name="developer" type="text"><br><br></td>
            <td>퍼블리셔<br><input name="publisher" type="text"><br><br></td>
            <td>출시일<br><input type="text" id="Date" name="publishing_date"><br><br></td>
          </tr>
          <tr>
            <td>태그<br><input name="genre" type="text"></td>
            <td>게임이용등급<br><input name="rate" type="text"></td>
            <td>플랫폼<br><input name="platform" type="text"></td>
          </tr>
        </table>
      </div>
    </div>
  </div>

  <br>
  <br>
	<div align="center">
		<input type="submit" value="등록하기">
		<input type="reset" value="취소">
	</div>
	
</form>
</c:if>

<c:if test="${sessionScope.managerId == null}">
<br><br><br><br><br>
<p style="color: red">접근권한이 없습니다.</p>
</c:if>


</body>
</html>
