<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<title>Persons list</title>


</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div id="content">
		<table>
			<thead>
				<tr class="">
					<td>Fornavn</td>
					<td>Etternavn</td>
					<td>Tlf</td>
					<td>Adresse</td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="person" items="${persons}">
					<tr>
						<td>${person.firstName}</td>
						<td>${person.lastName}</td>
						<td>${person.phone}</td>
						<td>${person.address}</td>
						<td><a href="PersonController?personID=${person.ID}">Endre</a></td>
						<td></td>
						
					</tr>
				</c:forEach>

			</tbody>
			<tfoot>
			<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="PersonController?personID=0">Ny person</a></td>
						<td></td>
						
					</tr>
			
			</tfoot>
		</table>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>