/**
 * 회원 가입
 */

var idCheck = false;
var pwCheck = false;
var emailCheck = false;
var emailVerify = false;
var emailKey = null;
var mailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;


function formSubmit(){
	var form = document.signUpForm;
	
	if(form.memberName.value == ""){
		alert("이름을 입력해 주십시오.");
		form.memberName.focus();
		return false;
	}
	
	if(form.memberId.value == "" || !idCheck){
		alert("아이디를 확인해 주십시오.");
		form.memberId.focus();
		return false;
	}
	
	if(form.memberPw.value == "" || !pwCheck){
		alert("비밀번호를 확인해 주십시오.");
		form.memberPw.focus();
		return false;
	}
	
	if(form.memberEmail.value == "" || !emailCheck || !emailVerify){
		alert("이메일을 확인해 주십시오.");
		form.memberEmail.focus();
		return false;
	}
	
	if($('input[name="agree"]').is(":checked") == false){
		alert("필수 약관에 동의해 주십시오.");
		form.agree.focus();
		return false;
	}
	
	form.submit();
}

function checkId(id){
	idCheck = false;
	
	if(id == ""){
		$("#idCheck").css("color", "#444444");
		$("#idCheck").text("아이디를 작성해 주십시오.");
	}else {
		$.ajax({
			url:contextPath + "/member/MemberCheckIdAction.me?id="+id,
			type:"get",
			dataType:"text",
			success:function(result){
				if(result.trim() == "ok"){
					idCheck = true;
					$("#idCheck").css("color", "#49c8ff");
					$("#idCheck").text("사용할 수 있는 아이디입니다.");
				}else{
					$("#idCheck").css("color", "red");
					$("#idCheck").text("중복된 아이디입니다.");
				}
			},
			error:function(){ // 통신 오류 시
				console.log("오류");
			}
		});
	}
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

// 키 이벤트 함수
$("input[name='memberId']").keyup(function(event){
	var id = $("input[name='memberId']").val();
	checkId(id);
})

$("input[name='memberPw']").keyup(function(event){
	var pw1 = $("input[name='memberPw']").val();
	var pw2 = $("input[name='memberPwCheck']").val();
	checkPw(pw1, pw2);
})

$("input[name='memberPwCheck']").keyup(function(event){
	var pw1 = $("input[name='memberPw']").val();
	var pw2 = $("input[name='memberPwCheck']").val();
	checkPw(pw1, pw2);
})

function sendEmail() {
	
	var memberEmail = $("#memberEmail").val();
	
	if( memberEmail == ""){
		$("#emailCheck").css("color", "#444444");
		$("#emailCheck").text("이메일을 입력해 주십시오.");
	}else if(!mailReg.test(memberEmail)){
		$("#emailCheck").css("color", "#444444");
		$("#emailCheck").text("이메일 형식에 맞게 입력해 주십시오.");
	}else if (!emailCheck){
		$("#emailCheck").css("color", "red");
		$("#emailCheck").text("중복된 이메일입니다.");
	}else {
		$("#memberEmailVer").hide();
		$("input[name='memberEmailCheck']").show();
		$.ajax({
			url:contextPath + "/util/EmailSendAction.me?memberEmail="+ memberEmail,
			type:"get",
			dataType:"text",
			error:function(){ // 통신 오류 시
				console.log("오류");
			},
			success:function(result){
				emailKey = result;
			}
		});
	}
}

function verifyEmail(email){
	emailVerify = false;
	if(email == emailKey){
		emailVerify = true;
		$("#emailCheck").css("color", "#49c8ff");
		$("#emailCheck").text("이메일 인증이 완료되었습니다.");
	}else {
		$("#emailCheck").css("color", "red");
		$("#emailCheck").text("인증 번호를 확인해 주십시오.");
	}
}

$("input[name='memberEmailCheck']").keyup(function(event){
	var email = $("input[name='memberEmailCheck']").val();
	verifyEmail(email);
})

function checkEmail(email){
	emailCheck = false;
	$("#memberEmailVer").show();
	$("input[name='memberEmailCheck']").hide();
	
	if(email == ""){
		$("#emailCheck").css("color", "#444444");
		$("#emailCheck").text("이메일을 입력해 주십시오.");
	}else if(!mailReg.test(email)){
		$("#emailCheck").css("color", "#444444");
		$("#emailCheck").text("이메일 형식에 맞게 입력해 주십시오.");
	}else {
		$.ajax({
			url:contextPath + "/member/MemberCheckEmailAction.me?email="+email,
			type:"get",
			dataType:"text",
			success:function(result){
				if(result.trim() == "ok"){
					emailCheck = true;
					$("#emailCheck").css("color", "red");
					$("#emailCheck").text("이메일 인증을 진행해 주십시오.");
				}else{
					$("#emailCheck").css("color", "red");
					$("#emailCheck").text("중복된 이메일입니다.");
				}
			},
			error:function(){ // 통신 오류 시
				console.log("오류");
			}
		});
	}
}

$("input[name='memberEmail']").keyup(function(event){
	var email = $("input[name='memberEmail']").val();
	checkEmail(email);
})