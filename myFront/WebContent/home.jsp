<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<!--
	Indivisible by Pixelarity
	pixelarity.com | hello@pixelarity.com
	License: pixelarity.com/license
-->
<html>
<head>
	<title>Untitled</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
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

	<!-- Wrapper -->
		<div id="wrapper">

			<!-- Home -->
				<article id="home" class="panel special">
					<div class="image">
						<img src="images/pic10.jpg" alt="" data-position="center center" />
					</div>
					<div class="content">
						<div class="inner">
							<header>
								<h1>Indivisible</h1>
								<p>Ipsum feugiat lorem sed magna<br />
								etiam adipiscing phasellus</p>
							</header>
							<nav id="nav">
								<ul class="actions stacked special animated spinY">
								<c:choose>
								<c:when test="${sessionId eq null}">
									<li><a href="${pageContext.request.contextPath}/member/MemberSignUp.me" class="button">Sign Up</a></li>
									<li><a href="${pageContext.request.contextPath}/member/MemberLogin.me" class="button">Login</a></li>																
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath}/board/BoardList.bo" class="button">게시판</a></li>
									<li><a href="${pageContext.request.contextPath}/member/MemberLogout.me" class="button">Logout</a></li>
								</c:otherwise>
								</c:choose>
								</ul>
							</nav>
						</div>
					</div>
				</article>

			<!-- Footer -->
				<footer id="footer">
					<p class="copyright">&copy; mamulee</p>
				</footer>

		</div>

	<!-- Scripts -->
		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/browser.min.js"></script>
		<script src="assets/js/breakpoints.min.js"></script>
		<script src="assets/js/util.js"></script>
		<script src="assets/js/main.js"></script>

</body>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
</html>