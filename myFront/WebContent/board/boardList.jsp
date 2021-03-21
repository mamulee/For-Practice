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
	<c:if test="${sessionId eq null}">
		<script>
			alert("로그인 후 이용 가능합니다.");
			location.replace("${pageContext.request.contextPath}/member/MemberLogin.me");
		</script>
	</c:if>
	<c:set var="list" value="${boardList}"/>
	<c:set var="totalCnt" value="${totalCnt}"/>
	<c:set var="startPage" value="${startPage}"/>
	<c:set var="endPage" value="${endPage}"/>
	<c:set var="nowPage" value="${curPage}"/>
	<c:set var="realEndPage" value="${realEndPage}"/>
	
	<!-- Wrapper -->
	<div id="wrapper">
	<!-- 글 쓰기 -->
	<article id="boardList" class="panel secondary">
		<div class="image">
			<img src="${pageContext.request.contextPath}/images/me.jpg" alt="" data-position="bottom center" />
		</div>
		<div class="content">
			<ul class="actions animated spinX">
				<li><a href="${pageContext.request.contextPath}/Home.me" class="button small back">Back</a></li>
			</ul>
			<div class="inner">
				<header>
					<h2>게시판</h2>
				</header>
				<div class="table-wrapper">
					<table class="alt">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>날짜 시간</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
						<c:choose>
						<c:when test="${boardList != null and fn:length(boardList) > 0}">
							<c:forEach var="b_vo" items="${boardList}">
							<tr>
								<td>${b_vo.getBoardNum()}</td>
								<td><a href="${pageContext.request.contextPath}/board/BoardView.bo?boardNum=${b_vo.getBoardNum()}&page=${curPage}">${b_vo.getBoardTitle()}</a></td>
								<td>${b_vo.getBoardId()}</td>
								<td>${b_vo.getBoardDate()}</td>
								<td>${b_vo.getReadCount()}</td>
							</tr>								
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5" align="center" style="border-style: none; background: white;">등록된 게시글이 없습니다.</td>
							</tr>
						</c:otherwise>
						</c:choose>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="3"></td>
								<td colspan="2" align="right">글 개수 : <c:out value="${totalCnt}"/>개</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="table-wrapper">
					<table>
						<tfoot>
							<tr>
								<td>
								<c:if test="${curPage > 1}">
									<a href="${pageContext.request.contextPath}/board/BoardList.bo?page=${curPage-1}">이전</a>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<c:choose>
										<c:when test="${i eq nowPage}">[${i}]&nbsp;</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath}/board/BoardList.bo?page=${i}">${i}&nbsp;</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${realEndPage != curPage}">
									<a href="${pageContext.request.contextPath}/board/BoardList.bo?page=${curPage+1}">다음</a>
								</c:if>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<ul class="actions stacked special animated spinY" style="margin-top: 50px;">
					<li>
						<input type="button" onclick="goWrite(${curPage})" value="글 쓰기" class="button primary" style="float:right;"/>
					</li>
				</ul>
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
<script src="boardList.js"></script>
</html>