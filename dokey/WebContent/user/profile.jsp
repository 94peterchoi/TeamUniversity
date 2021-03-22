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

<title>프로필 조회</title>
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
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="profile.do?userId=${dto.id}" role="tab" aria-controls="home" aria-selected="true">유저정보</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="viewPoint.do?userId=${dto.id}" role="tab" aria-controls="profile" aria-selected="false">포인트내역</a>
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
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>User Id</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${dto.id}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Name</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${dto.nickname}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Email</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${dto.email}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Phone</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${dto.hp}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Point</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${dto.point}</p>
                                            </div>
                                        </div>
                            </div>
                            
                            
<!--                             <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Experience</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Expert</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Hourly Rate</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>10$/hr</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Total Projects</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>230</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>English Level</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Expert</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Availability</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>6 months</p>
                                            </div>
                                        </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label>Your Bio</label><br/>
                                        <p>Your detail description</p>
                                    </div>
                                </div>
                            </div>
 -->                        
 
 						</div>
                    </div>
                </div>
            </form>           
        </div>  

  

</body>
</html>