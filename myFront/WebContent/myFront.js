/**
 * 
 */

function check() {
	$("#email-confirm-btn-d").hide();
	$("#email-confirm-btn").hide();
	$("#email-confirm").show();
}

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
