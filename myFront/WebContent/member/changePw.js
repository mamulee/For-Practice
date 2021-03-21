/**
 * 
 */

var pwCheck = false;

function formSubmit(){
	var form = document.changePwForm;

	if(!pwCheck){
		alert("비밀번호를 확인해 주십시오.");
		form.newPwCheck.focus();
		return false;
	}
	
	form.submit();
}

function checkPw(pw1, pw2){
	pwCheck= false;
	
	if(pw1 == "" || pw2 == ""){
		$("#pwCheck").css("color", "#444444");
		$("#pwCheck").text("비밀번호를 입력해 주십시오.");
	}else if(pw1 == pw2){
		pwCheck = true;
		$("#pwCheck").css("color", "#49c8ff");
		$("#pwCheck").text("비밀번호가 일치합니다.");
	}else {
		$("#pwCheck").css("color", "red");
		$("#pwCheck").text("비밀번호가 일치하지 않습니다.");
	}
}

$("input[name='newPw']").keyup(function(event){
	var pw1 = $("input[name='newPw']").val();
	var pw2 = $("input[name='newPwCheck']").val();
	checkPw(pw1, pw2);
})

$("input[name='newPwCheck']").keyup(function(event){
	var pw1 = $("input[name='newPw']").val();
	var pw2 = $("input[name='newPwCheck']").val();
	checkPw(pw1, pw2);
})