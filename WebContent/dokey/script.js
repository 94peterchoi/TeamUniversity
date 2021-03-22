// dokey(비로그인 사용자) 관리하는 js코드


// login.jsp에서 호출 (로그인 화면 유효성 검사)
var way = 0;

function loginCheck() {
   if(!document.loginForm.id.value) {
      alert("아이디를 입력하세요");
      document.loginForm.id.focus();
      return false;
   }
   
   if(!document.loginForm.pwd.value) {
      alert("비밀번호를 입력하세요");
      document.loginForm.pwd.focus();
      return false;
   }
}


// signin.jsp에서 호출 (회원가입 유효성 검사)
function signinCheck() {
	
	var check = 0;
	
	if(document.signinForm.nickname.value.length < 2 || document.signinForm.nickname.value.length >= 7) {
		document.signinForm.nickname.focus();
		var x = document.getElementById("nicknameError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("nicknameError");
		x.style.display = "none";
		check = check + 1;
	}
	
	if(!document.signinForm.email.value) {
		document.signinForm.email.focus();
		var x = document.getElementById("emailError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("emailError");
		x.style.display = "none";
		check = check + 1;
	}
	
	if(!document.signinForm.id.value) {
		document.signinForm.id.focus();
		var x = document.getElementById("idError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("idError");
		x.style.display = "none";
		check = check + 1;
	}
	
	if(!document.signinForm.pwd.value) {
		document.signinForm.pwd.focus();
		var x = document.getElementById("pwdError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("pwdError");
		x.style.display = "none";
		check = check + 1;
	}
	
	if(document.signinForm.repwd.value != document.signinForm.pwd.value) {
		document.signinForm.repwd.focus();
		var x = document.getElementById("repwdError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("repwdError");
		x.style.display = "none";
		check = check + 1;
	}
	
	if(!document.signinForm.hp.value || !(document.signinForm.hp.value.length == 13)) {
		document.signinForm.hp.focus();
		var x = document.getElementById("hpError");
		x.style.display = "inline";
		check = 0;
	} else {
		var x = document.getElementById("hpError");
		x.style.display = "none";
		check = check + 1;
	}
	
	if (document.signinForm.hiddenNum.value == 0 && document.signinForm.id.value.length >= 1) {
		var url="confirmId.do?id=" + document.signinForm.id.value;
		window.open(url, "confirm", "menubar=no, width=400, height=300");
	}
	
	console.log(check);
	console.log(document.signinForm.hiddenNum.value);
	
	
	if (check == 6 && document.signinForm.hiddenNum.value == 1) {
		return true;
	} else {
		return false;
	}
	
}

// 아이디 중복확인 통과
function setNum() {
	window.opener.document.signinForm.hiddenNum.value = 1;
	self.close();
}


// detail.jsp에서 호출 (장바구니담기버튼 클릭시)
function pick(code, num, pageNum, number) {
	
	console.log(code);
	console.log(num);
	console.log(pageNum);
	console.log(number);
	
	var url = "&num=" + num + "&pageNum=" + pageNum + "&number=" + number;
	console.log(url);
	
	alert("장바구니에 상품을 담았습니다.");
	window.location="wishlist.do?code=" + code + url;
	
}



 