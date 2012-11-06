<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
						value="${article.publishedDate}" /> | Skrevet av 
					${article.author.personalia.firstName}
					${article.author.personalia.lastName} 
					
				</div>
				<div class="article_content">${article.content}</div>
				<div class="article_footer"></div>
			</div>

		</c:forEach>



	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>