<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="header.jsp" />
<title>Home | O-løp er OK</title>





</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>

	<div id="content">

		<c:forEach var="article" items="${articles}">

			<div class="article">
				<div class="article_title">
					<h3>${article.title}</h3>
				</div>
				<div class="article_head">
					<fmt:formatDate type="BOTH" dateStyle="long" timeStyle="long"
						value="${article.publishedDate}" />
					| Skrevet av ${article.author.personalia.firstName}
					${article.author.personalia.lastName}

				</div>
				<div class="article_content">
					<c:set var="content" value="${article.content}" />
					<c:choose>
						<c:when test="${fn:length(articles) gt 1}">

							<c:choose>
								<c:when test="${fn:length(article.content) >200 }">
									<c:set var="substr" value="${fn:substring(content,-1,200)}..." />
									<p>${substr}</p>

								</c:when>
								<c:otherwise>
							${article.content}
						</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>

						${article.content}

						</c:otherwise>
					</c:choose>
					
					<c:if test="${fn:length(articles) gt 1}">

						<p>
							<a href="HomeController?articleID=${article.ID}" class="btn">Les
								mer..</a>
						</p>
					</c:if>

				</div>
				<div class="article_footer"></div>
			</div>

		</c:forEach>



	</div>
	<script type="text/javascript"
		src="http://www.yr.no/sted/Norge/Hordaland/Bergen/Bergen/ekstern_boks_liten.js"></script>
	<noscript>
		<a href="http://www.yr.no/sted/Norge/Hordaland/Bergen/Bergen/">yr.no:
			Værvarsel for Bergen</a>
	</noscript>
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>