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
	function validate() {
		
		
		var pList = document.getElementById("ddlPersons");
		var txtuser = document.getElementById("txtuser");
		var pwd = document.getElementById("pwd");
		var repwd = document.getElementById("txtpwd2");
		
		var ex = new Array();
		var valid = true;
		
		if (isBlank(txtuser.value)){
			ex[0] = "*Brukernavn påkrevd";
			valid = false;
		}
		
		if(pList[pList.selectedIndex].value == "0"){
			ex[(ex.length)] = "*Personalia må velges";
			valid = false;
		}
		
		if(pwd.value != repwd.value){
			ex[(ex.length)] = "*Passordene må være like";
			valid = false;
		}
		
		if(!valid){
			alert("Vennligst korriger følgende mangler: \n" + ex.join("\n"));
		}
		return valid;
	}
	function isBlank(str) {
		return (!str || /^\s*$/.test(str));
	}
</script>

</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div id="content">
		<form method="post" action="UserController"
			>
			<table>
				<tr>
					<td>Personalia</td>

					<td><select id="ddlPersons" name="personalia"
						onchange="selectedIndexChange()">
							<option value="0">-Velg-</option>
							<c:forEach var="person" items="${persons}">


								<c:choose>
									<c:when test="${person.ID eq user.personalia.ID}">
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
					<td><input id="txtuser" type="text" name="username"
						value="${user.userName}" /></td>
				</tr>
				<tr>
					<td>Passord</td>
					<td><input id="pwd" type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Verifiser passord</td>
					<td><input id="txtpwd2" type="password" name="rePassword" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="userID" value="${user.userId}" />
					</td>
					<td><input type="submit" value="Lagre"
						 onclick="return validate()"/></td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>