// user 파일 관련 javascript 소스코드

// 회원정보 수정 유효성 검사 - updateProfileView에서 호출
function updateCheck() {
	
	var check = 0;	// 0이면 불합격, 1이면 통과
	
	if(document.updateForm.nickname.value.length < 2 || document.updateForm.nickname.value.length >= 7) {
		document.updateForm.nickname.focus();
		var x = document.getElementById("nicknameError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("nicknameError");
		x.style.display = "none";
		check = check + 1;
	}
	
	if(!document.updateForm.email.value) {
		document.updateForm.email.focus();
		var x = document.getElementById("emailError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("emailError");
		x.style.display = "none";
		check = check + 1;
	}
	
	
	if(!document.updateForm.pwd.value) {
		document.updateForm.pwd.focus();
		var x = document.getElementById("pwdError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("pwdError");
		x.style.display = "none";
		check = check + 1;
	}
	
	if(document.updateForm.repwd.value != document.updateForm.pwd.value) {
		document.updateForm.repwd.focus();
		var x = document.getElementById("repwdError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("repwdError");
		x.style.display = "none";
		check = check + 1;
	}
	
	if(!document.updateForm.hp.value || !(document.updateForm.hp.value.length == 13)) {
		document.updateForm.hp.focus();
		var x = document.getElementById("hpError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("hpError");
		x.style.display = "none";
		check = check + 1;
	}
	
	console.log(check);
	
	if (check == 5) {
		return true;
	} else {
		return false;
	}
}


// 수정취소버튼 - 홈화면으로 가기 updateProfileView.jsp에서 호출
function goHome() {
	window.location='home.do';
}


// 회원탈퇴 버튼 클릭 (탈퇴의사 재확인) - deleteProfileView.jsp에서 호출
function reCheck() {
	if(confirm("정말로 탈퇴하시겠습니까?")) {
		alert("탈퇴완료!");
		return true;
	} else {
		return false;
	}
}


// <textarea id="suggestion> 글자수 제한 관련 (100자로 제한)
$(document).ready(function() {
    $('#suggestion').on('keyup', function() {
        $('#test_cnt').html("("+$(this).val().length+" / 100)");

        if($(this).val().length > 100) {
            $(this).val($(this).val().substring(0, 100));
            $('#test_cnt').html("(100 / 100)");
        }
    });
});



//buy.jsp에서 호출 (결제수단1[포인트] 선택)
function clickPay1() {
	$("#pay1").css("border", "2px solid green");
	$("#pay2").css("border", "none");
	way = 1;
	console.log(way);
}

// buy.jsp에서 호출 (결제수단2[신용카드] 선택)
function clickPay2() {
	$("#pay2").css("border", "2px solid green");
	$("#pay1").css("border", "none");
	way = 2;
	console.log(way);
}

// buy.jsp에서 호출  (구매하기 버튼 클릭시)
function nowBuy(price, point, code) {
	if (way == 2) {
		alert("현재는 포인트 결제만 가능합니다.");
		return false;
	} else if (way == 1){
		if (price > point) {
			alert("포인트가 부족합니다.");
			return false;
		} else {
			if(confirm("정말로 구매하시겠습니까?")) {
				var str1 = "finishBuy.do?code=";
				var str2 = code;
				console.log(str1.concat(str2));
				alert("구매완료!");
				window.location=str1.concat(str2);
//				window.location='finishBuy.do';
			}
		}
	} else {
		alert("결제방법을 선택해주세요");
	}
}


// buylist.jsp에서 호출 (구매이력 화면에서 환불버튼 클릭시)
function refundGame(code, price) {
	
	if(confirm("정말로 환불하시겠습니까?")) {
		alert("환불완료!");
		window.location="refundGame.do?code=" + code + "&price=" + price;
		
	}
}

// wishlist.jsp에서 호출 (장바구니 특정상품 삭제버튼)
function deleteOne(gamecode) {
	alert("삭제완료!");
	window.location="deleteWishlist.do?code=" + gamecode;
} 	


// addPoint.jsp에서 호출 (포인트충전 인풋버튼 클릭할 때마다 실행되는 함수)
function changePoint(point, money) {
	var p = document.getElementById("pointSpan");
	var m = document.getElementById("moneySpan");
	
	p.innerHTML = point + "p";
	m.innerHTML = money + "원";
	
	console.log(point);
	console.log(money);
}

// addPoint.jsp에서 호출 (form 제출 유효성 검사)
function payCheck() {
	
	if(document.addPointForm.cardname.value.length < 2) {
		alert("카드 명의자를 입력하세요.");
		document.addPointForm.cardname.focus();
		return false;
	} else if(document.addPointForm.cardnum.value.length != 19) {
		alert("일련번호를 1111-2222-3333-4444 형태로 입력하세요.");
		document.addPointForm.cardnum.focus();
		return false;
	} else if(document.addPointForm.exp.value.length != 5) {
		alert("만료년/월을 입력하세요 (예: 07/25)");
		document.addPointForm.exp.focus();
		return false;
	} else if(document.addPointForm.cvc.value.length != 3) {
		alert("cvc 3자리를 입력하세요.");
		document.addPointForm.cvc.focus();
		return false;
	} else if(!document.addPointForm.point.value) {
		alert("충전할 포인트금액을 선택하세요");
		return false;
	}
	
	if (confirm("해당 정보로 결제를 진행하시겠습니까?")) {
		alert("결제완료!");
		window.location="addPointAction.do";
		return true;
	} else {
		return false;
	}
	
}
