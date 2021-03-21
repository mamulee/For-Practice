<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<!-- 글 쓰기 -->
	<article id="boardWrite" class="panel secondary">
		<div class="image">
			<img src="${pageContext.request.contextPath}/images/me.jpg" alt="" data-position="bottom center" />
		</div>
		<div class="content">
			<ul class="actions animated spinX">
				<li><a href="${pageContext.request.contextPath}/board/BoardList.bo" class="button small back">Back</a></li>
			</ul>
			<div class="inner">
				<header>
					<h2>게시글</h2>
				</header>
				<form name="boardForm" method="post" action="${pageContext.request.contextPath}/board/BoardWriteAction.bo">
					<div class="fields">
						<div class="field half">
							<label for="name">제목</label>
							<input type="text" name="boardTitle" id="memberName" />
							<br>
							<label for="id">글쓴이</label>
							<input type="text" name="boardId" id="id" value="${sessionId}" readonly />
						</div>
						<div class="field">
							<label for="boardContent">내용</label>
							<textarea name="boardContent" id="message" rows="5" style="resize:none"></textarea>
						</div>
					</div>
					<ul class="actions stacked special animated spinY" style="margin-top: 50px;">
						<li>
							<input type="button" onclick="formSubmit()" value="등록" class="button primary" style="float:left;"/>
							<input type="reset" value="Reset" class="button" style="float:right;" />
						</li>
					</ul>
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
<script src="//code.jquery.com/jquery-3.4.1.min.js"></script>
<script>var contextPath = "${pageContext.request.contextPath}";</script>
<script src="boardWrite.js"></script>
</html>