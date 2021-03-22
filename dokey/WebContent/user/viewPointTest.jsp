<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="${user_path}profile.css" rel="stylesheet" id="profile.css">

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>고객 - 포인트 충전내역 조회</title>
<style>

.container {
	background-color: white !important;
}

</style>

</head>
<body>

<jsp:include page="navigation.jsp" />

  <br><br><br><br><br>


<div class="container emp-profile">
            <form id="profile-form" method="post" action="profileUpdate" >
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                            <img src="${dto.profile_img}" style="width:230px; height:160px;">
                            <br><br>
	                        <div class="file btn btn-lg btn-primary">
                               	프로필사진
                            </div>
                       
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                                    <h5>
                                        ${nickname}
                                    </h5>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">							
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="profile.do?userId=${dto.id}" role="tab" aria-controls="home" aria-selected="false">유저정보</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="viewPoint.do?userId=${dto.id}" role="tab" aria-controls="profile" aria-selected="true">포인트내역</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2">
<!--                         <input type="submit" class="profile-edit-btn" name="btnAddMore" value="회원정보수정"/>
 -->                        <input type="button" class="profile-edit-btn" name="btnAddMore" value="회원정보수정"
 								onclick="window.location='updateProfile.do'"/>
 							<br><br>
 							<input type="button" class="profile-edit-btn" name="btnAddMore" value="회원탈퇴"
 								onclick="window.location='deleteProfile.do'"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                            <p>나의 활동</p>
                            <a href="">보유 게임수: ${dto.count_game}</a><br/>
                            <a href="">작성 리뷰수: ${dto.count_review}</a><br/>
                            <a href="#">마지막 접속일자 : ${dto.last_date}</a>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            
								<c:if test="${cnt > 0}">
										<table>
											<tr>
												<th>충전날짜</th>			
												<th>포인트</th>			
												<th>결제방법</th>			
											</tr>
											
											<c:forEach var="dto2" items="${dtos}">
											<tr>
												<td>${dto2.pay_day}</td>			
												<td>+${dto2.point}원</td>			
												<td>${dto2.payment}</td>			
											</tr>
											</c:forEach>
										</table>
								</c:if>
								
								<!-- 페이지 컨트롤 -->
								<table style="width:500px; margin-left:auto; margin-right: auto; color:white; font-size:30px;">
									<tr>
										<th align="center">
											<c:if test="${cnt >0}">
												<c:if test="${startPage > pageBlock}">
													<a href="viewPoint.do?&pageNum=${startPage - pageBlock}">[◀]</a>
												</c:if>
												
												<c:forEach var="i" begin="${startPage}" end="${endPage}">
														<c:if test="${i == currentPage}">
															<span><b> [${i}] </b></span>
														</c:if>
														
														<c:if test="${i != currentPage}">
															<a href="viewPoint.do?pageNum=${i}"> [${i}] </a>
														</c:if>
								
												
												</c:forEach>
												
												<c:if test="${pageCount > endPage}">
													<a href="viewPoint.do?pageNum=${startPage + pageBlock}"> [▶]</a>
												</c:if>
											</c:if>
										</th>
									</tr>
								</table>
                            
                            </div>
 						</div>
                    </div>
                </div>
            </form>           
        </div>  

  

</body>
</html>