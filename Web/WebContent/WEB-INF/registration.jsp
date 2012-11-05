<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />
<title>Registration</title>


</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div id="content">
		<form>
			<table method="post" action="UserController">
				<tr>
					<td>Personalia</td>
					<td><select name="persons">
					 <option value="0">-Velg-</option>
					</select></td>
				</tr>
				<tr>
					<td>Emergency contact</td>
					<td><select name="ec">
					 <option value="0">-Velg-</option>
					</select></td>
				</tr>
				<tr>
					<td>Friend</td>
					<td><select name="friend">
					 <option value="0">-Velg-</option>
					</select></td>
				</tr>
				<tr>
					<td>Brukernavn</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Passord</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Lagre" /></td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>