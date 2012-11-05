<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div id="content">
		<form>
			<table method="post" action="UserController">
				<tr>
					<td>Fornavn</td>
					<td><input type="text" name="firstname" /></td>
				</tr>
				<tr>
					<td>Etternavn</td>
					<td><input type="text" name="lastname" /></td>
				</tr>
				<tr>
					<td>Brukernavn</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Brukernavn</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Passord</td>
					<td><input type="password" name="password" /></td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>