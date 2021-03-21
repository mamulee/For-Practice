/**
 * 
 */
function findPw() {
	$("#findId").css("color", "#555");
	$("#findPw").css("color", "#49c8ff");
	$("#find-id").hide();
	$("#find-pw").show();
}

function sendLink(){
	var id = $("input[name='memberId']").val();
	var email = $("input[name='memberPwEmail']").val();
	if(id == ""){
		$("#findPwCheck").css("color", "red");
		$("#findPwCheck").text("아이디를 입력해 주십시오.");
		$("input[name='memberId']").focus();
	}else if(email == ""){
		$("#findPwCheck").css("color", "red");
		$("#findPwCheck").text("이메일을 입력해 주십시오.");
		$("input[name='memberPwEmail']").focus();
	}else {
		$("#findPwCheck").css("color", "#49c8ff");
		$("#findPwCheck").text("요청을 처리 중입니다.");
		$.ajax({
			url:contextPath + "/member/MemberFindPwAction.me?memberId="+id+"&memberEmail="+email,
			type:"get",
			dataType:"text",
			success:function(result){
				if(result.trim() == "ok"){
					alert("링크 전송이 완료되었습니다. 이메일을 확인해 주십시오.");
					location.reload();
				}else{
					$("#findPwCheck").text("");
					alert("일치하는 회원 정보가 없습니다. 다시 시도해 주십시오.");
					//location.reload();
				}
			},
			error:function(){ // 통신 오류 시
				console.log("오류");
			}
		});
	}
}