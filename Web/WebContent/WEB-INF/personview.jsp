<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />
<title>Persons</title>


</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div id="content">
		<form method="post" action="PersonController">
			<table>
				<tr>

					<td>Fornavn</td>
					<td><input type="text" name="firstName" value="${person.firstName}" /></td>
				</tr>
				<tr>
					<td>Etternavn</td>
					<td><input type="text" name="lastName" value="${person.lastName}"/></td>
				</tr>
				<tr>
					<td>Tlf. nr.:</td>
					<td><input type="text" name="phone" value="${person.phone}"/></td>
				</tr>
				<tr>
					<td>Fødselsår</td>
					<td><input type="number" name="birthYear" value="${person.birthYear}"/></td>
				</tr>
				<tr>
					<td>Adresse</td>
					<td><input type="text" name="address" value="${person.address}"/></td>
				</tr>
				<tr>
					<td><input type="hidden" name="personID" value="${person.ID}"/></td>
					<td><input type="submit" value="Lagre"}"/></td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>