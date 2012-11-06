<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />
<title>Persons</title>

<script type="text/javascript">
	function validate() {
		var fname = document.getElementById("txtfn");
		var lname = document.getElementById("txtln");
		var phone = document.getElementById("txtphone");
		var birth = document.getElementById("txtbirth");
		var addr = document.getElementById("txtaddr");

		var ctrls = new Array(fname, lname, phone, birth, addr);
		var valid = true;

		
		
		for ( var i = 0; i < ctrls.length; i++) {
			if (isBlank(ctrls[i].value)){
				valid = false;
				ctrls[i].style.borderColor = "#FF0000";
			}else {
				ctrls[i].style.borderColor = null;
			}

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
		<form method="post" action="PersonController" onsubmit="return validate()">
			<table>
				<tr>

					<td>Fornavn</td>
					<td><input id="txtfn" type="text" name="firstName"
						value="${person.firstName}" /></td>
				</tr>
				<tr>
					<td>Etternavn</td>
					<td><input id="txtln" type="text" name="lastName"
						value="${person.lastName}" /></td>
				</tr>
				<tr>
					<td>Tlf. nr.:</td>
					<td><input id="txtphone" type="text" name="phone"
						value="${person.phone}" /></td>
				</tr>
				<tr>
					<td>Fødselsår</td>
					<td><input id="txtbirth" type="number" name="birthYear"
						value="${person.birthYear}" /></td>
				</tr>
				<tr>
					<td>Adresse</td>
					<td><input id="txtaddr" type="text" name="address"
						value="${person.address}" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="personID" value="${person.ID}" /></td>
					<td><input onclick="return validate()" type="submit" value="Lagre"/></td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>