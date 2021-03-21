<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Untitled</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
	<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	<style>
		@media screen and (max-width: 480px) {
			ul > li > .button {
				float: none;
			    width: 100%;
			    margin-top: 10px;
			}
		}
		
		textarea {
			font-size: 0.9em;
		}
	</style>
</head>
<body class="is-preload-0 is-preload-1">
	<!-- Wrapper -->
	<div id="wrapper">
	<!-- Find Id & Pw -->
	<article id="find-id-pw" class="panel secondary">
		<div class="image">
			<img src="${pageContext.request.contextPath}/images/pic11.jpg" alt="" data-position="bottom center" />
		</div>
		<div class="content">
			<ul class="actions animated spinX">
				<li><a href="${pageContext.request.contextPath}/Home.me" class="button small back">Home</a></li>
			</ul>
			
			<header>
				<h2><a href="javascript: findId()" id="findId" style="border: none; color:#49c8ff;">아이디 찾기</a> / <a href="javascript: findPw()" id="findPw" style="border: none;">비밀번호 찾기</a></h2>
			</header>
			<div class="inner" id="find-id">
			<!-- 아이디 찾기 -->
					<p>
						가입 시 인증한 이메일 주소를 입력하시면 이메일로 아이디를 보내드립니다.
					</p>
				<form method="post" action="#">
					<div class="fields">
						<div class="field half">
							<label for="email-find">Email</label>
							<input type="email" name="memberEmail" id="email-find"/>
							<ul class="actions" style="width:100%; margin-bottom:0;">
								<li style="width:100%;"><a href="javascript: sendId()" id="find-send" class="button" style="width:100%; margin-top:1em;">아이디 발송</a></li>
							</ul>
							<textarea id="findIdCheck" rows="1" cols="1" style="resize:none; outline:none; border:none; background:white; text-align:right;" readonly></textarea>
						</div>
					</div>
				</form>
			</div>
			<!-- 비밀번호 찾기 -->
			<div class="inner" id="find-pw" style="display: none;">
					<p>
						가입 시 입력한 아이디와 인증한 이메일 주소를 입력하시면 이메일로 비밀번호를 재설정 할 수 있는 링크를 보내드립니다.
					</p>
				<form method="post" action="#">
					<div class="fields">
						<div class="field half">
							<label for="id-find">Id</label>
							<input type="text" name="memberId" id="id-find"/>
							<label for="email-find" style="margin-top:1em;">Email</label>
							<input type="email" name="memberPwEmail" id="email-find"/>
							<ul class="actions" style="width:100%; margin-bottom:0;">
								<li style="width:100%;"><a href="javascript: sendLink()" id="find-send" class="button" style="width:100%; margin-top:1em;">링크 발송</a></li>
							</ul>
							<textarea id="findPwCheck" rows="1" cols="1" style="resize:none; outline:none; border:none; background:white; text-align:right;" readonly></textarea>
						</div>
					</div>
				</form>
			</div>

			<form>
				<div class="fields">
					<div class="field half">

						<p style="margin: 0 0 0.5em 0;">아이디가 기억나셨다면:</p>
						<ul class="actions" style="margin-bottom: 0; width: 100%;">
							<li style="width: 100%;"><a href="${pageContext.request.contextPath}/member/MemberLogin.me" id="find-login"
								class="button primary" style="width: 100%;">로그인 하러 가기</a></li>
						</ul>
						<br>
						<p style="margin: 0 0 0.5em 0;">새로 가입을 원하신다면:</p>
						<ul class="actions" style="margin-bottom: 0; width: 100%;">
							<li style="width: 100%;"><a href="${pageContext.request.contextPath}/member/MemberSignUp.me" id="find-signup"
								class="button" style="width: 100%;">회원가입 하러 가기</a></li>
						</ul>
					</div>
				</div>
			</form>

		</div>
	</article>
	<!-- Footer -->
	<footer id="footer">
		<p class="copyright">&copy; mamulee</p>
	</footer>

	</div>

	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/browser.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/breakpoints.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
<script src="findId.js"></script>
<script src="findPw.js"></script>
</html>