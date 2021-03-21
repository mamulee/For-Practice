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
	</style>
</head>
<body class="is-preload-0 is-preload-1">
	<c:if test="${not empty param.login}">
		<c:if test="${param.login == 'false'}">
			<script>alert("아이디 혹은 비밀번호를 다시 확인해 주십시오.")</script>
		</c:if>
		<c:if test="${param.login == 'new'}">
			<script>alert('비밀번호가 변경되었습니다. 다시 로그인해 주십시오.');</script>
		</c:if>
	</c:if>
	<!-- Wrapper -->
	<div id="wrapper">
	<!-- Login -->
	<article id="login" class="panel secondary">
		<div class="image">
			<img src="${pageContext.request.contextPath}/images/pic09.jpg" alt="" data-position="bottom center" />
		</div>
		<div class="content">
			<ul class="actions animated spinX">
				<li><a href="${pageContext.request.contextPath}/Home.me" class="button small back">Back</a></li>
			</ul>
			<div class="inner">
				<header>
					<h2>Login</h2>
				</header>
				<form name="loginForm" method="post" action="${pageContext.request.contextPath}/member/MemberLoginAction.me">
					<div class="fields">
						<div class="field half">
							<label for="id-login">Id</label>
							<input type="text" name="loginId" id="id-login" />
							<br>
							<label for="pw-login">Password</label>
							<input type="password" name="loginPw" id="pw-login" />
							<br>
					<a href="${pageContext.request.contextPath}/member/MemberForget.me" style="float:right;">아이디 / 비밀번호 찾기</a>
					<br>
					<ul class="actions stacked special animated spinY"style="margin-top:30px;">
						<li>
							<a href="javascript:formSubmit()" class="button submit primary" style="min-width: 0;padding-left: 1.5em;padding-right: 1.5em; float:left;">Login</a>
							<a href="${pageContext.request.contextPath}/member/MemberSignUp.me" class="button submit" style="min-width: 0;padding-left: 1.2em;padding-right: 1em; float:right;">Sign Up</a>
						</li>
					</ul>
						</div>
					</div>
				</form>
			</div>
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
<script src="login.js"></script>
</html>