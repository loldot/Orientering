<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${empty access }">
		<div class="login">
			<form action="LoginController" method="post" name="LoginController">

				Brukernavn
				<div>
					<input type="text" name="username" />
				</div>
				Passord
				<div>
					<input type="password" name="password" />
				</div>
				<div>
					<input type="submit" value="Sign in" />
					<a href="UserController">Ny bruker</a>
				</div>



			</form>
		</div>
	</c:when>
	<c:otherwise>

		<div class="login">
			<p>Innlogget:</p>
			<div><b>${access.personalia.firstName}
				${access.personalia.lastName}</b></div>
			<div>
				<a href="LoginController?action=logout">Logg ut</a>
			</div>
		
		</div>

	</c:otherwise>
</c:choose>