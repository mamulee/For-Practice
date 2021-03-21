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
			font-size: 0.8em;
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
				<li><a href="${pageContext.request.contextPath}/Home.me" class="button small back">Back</a></li>
			</ul>
			
			<div class="inner" id="find-id">
			<header>
				<h2>비밀번호 변경</h2>
			</header>
			<!-- 비밀번호 변경 -->
					<p>
						새롭게 변경할 비밀번호를 입력하십시오.
					</p>
				<form name="changePwForm" method="post" action="${pageContext.request.contextPath}/member/MemberChangePwAction.me">
					<input type="hidden" name="memberId" value="${param.memberId}">
					<div class="fields">
						<div class="field half">
							<label for="pw">새 비밀번호</label>
							<input type="password" name="newPw" id="pw" />
							<br>
							<label for="pw-check">새 비밀번호 확인</label>
							<input type="password" name="newPwCheck" id="pw-check" />
							<textarea id="pwCheck" rows="1" cols="1" style="resize:none; outline:none; border:none; background:white; text-align:right;" readonly></textarea>
							<ul class="actions" style="width:100%; margin-bottom:0;">
								<li style="width:100%;"><a href="javascript:formSubmit()" id="find-send" class="button" style="width:100%; margin-top:1em;">확인</a></li>
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
<script src="changePw.js"></script>
</html>