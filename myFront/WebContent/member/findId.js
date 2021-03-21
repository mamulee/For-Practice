/**
 * 
 */

function findId() {
	$("#findId").css("color", "#49c8ff");
	$("#findPw").css("color", "#555");
	$("#find-id").show();
	$("#find-pw").hide();
}

function findPw() {
	$("#findId").css("color", "#555");
	$("#findPw").css("color", "#49c8ff");
	$("#find-id").hide();
	$("#find-pw").show();
}

function sendId(){
	var email = $("input[name='memberEmail']").val();
	if(email == ""){
		$("#findIdCheck").css("color", "red");
		$("#findIdCheck").text("이메일을 입력해 주십시오.");
	}else {
		$("#findIdCheck").css("color", "#49c8ff");
		$("#findIdCheck").text("요청을 처리 중입니다.");
		$.ajax({
			url:contextPath + "/member/MemberFindIdAction.me?memberEmail="+email,
			type:"get",
			dataType:"text",
			success:function(result){
				if(result.trim() == "ok"){
//					$("#findIdCheck").css("color", "#49c8ff");
//					$("#findIdCheck").text("아이디 전송이 완료되었습니다.");
					alert("아이디 전송이 완료되었습니다. 이메일을 확인해 주십시오.");
					location.reload();
				}else{
//					$("#findIdCheck").css("color", "red");
					$("#findIdCheck").text("");
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