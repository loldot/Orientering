<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<title>Registration</title>
<script type="text/javascript">
	function selectedIndexChange() {

		var pList = document.getElementById("ddlPersons");
		var ecList = document.getElementById("ddlEC");
		var fList = document.getElementById("ddlFriends");
		var pListItem = pList[pList.selectedIndex];

		for ( var i = 0; i < pList.length; i++) {
			if (pListItem.value == fList[i].value) {
				fList[i].disabled = true;
				ecList[i].disabled = true;
			} else {
				fList[i].disabled = false;
				ecList[i].disabled = false;
			}

		}

	}
</script>

</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div id="content">
		<form method="post" action="UserController">
			<table>
				<tr>
					<td>Personalia</td>

					<td><select id="ddlPersons" name="personalia">
							<option value="0">-Velg-</option>
							<c:forEach var="person" items="${persons}">

								<c:choose>
									<c:when test="${person.ID} == ${user.personalia.ID}">
										<option value="${person.ID}" selected="selected">${person.firstName}
											${person.lastName}</option>
									</c:when>
									<c:otherwise>
										<option value="${person.ID}">${person.firstName}
											${person.lastName}</option>

									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Emergency contact</td>
					<td><select id="ddlEC" name="ec">
							<option value="0">-Velg-</option>
							<c:forEach var="person" items="${persons}">

								<option value="${person.ID}">${person.firstName}
									${person.lastName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Friend</td>
					<td><select id="ddlFriends" name="friend">
							<option value="0">-Velg-</option>
							<c:forEach var="person" items="${persons}">
								<option value="${person.ID}">${person.firstName}
									${person.lastName}</option>
							</c:forEach>
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
					<td>Verifiser passord</td>
					<td><input type="password" name="rePassword" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="userID" value="${user.ID}" /> </td>
					<td><input type="submit" value="Lagre" /></td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>