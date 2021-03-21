/**
 * 
 */

function formSubmit(){
	var form = document.loginForm;

	if(form.loginId.value == ""){
		alert("아이디를 입력해 주십시오.");
		form.loginId.focus();
		return false;
	} else if(form.loginPw.value == ""){
		alert("비밀번호를 입력해 주십시오.");
		form.loginPw.focus();
		return false;
	}
	
	form.submit();
}