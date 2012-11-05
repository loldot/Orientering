<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<title>Brukere</title>


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
					<td>Fødselsår</td>
					<td>Kontaktperson</td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.personalia.firstName}</td>
						<td>${user.personalia.lastName}</td>
						<td>${user.personalia.phone}</td>
						<td>${user.personalia.address}</td>
						<td>${user.personalia.birthYear}</td>
						<td>${user.emergencyContact.firstName} ${user.emergencyContact.lastName}</td>
						<td><a href="UserController?userID=${user.userId}">Endre</a></td>
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
					<td><a href="UserController?userID=0">Ny person</a></td>
					<td></td>
					<td></td>
					<td></td>

				</tr>

			</tfoot>
		</table>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>