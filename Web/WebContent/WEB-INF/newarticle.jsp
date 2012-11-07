<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<title>Insert title</title>

<!-- Skin CSS file -->
<link rel="stylesheet" type="text/css"
	href="http://yui.yahooapis.com/2.9.0/build/assets/skins/sam/skin.css">
<!-- Utility Dependencies -->
<script
	src="http://yui.yahooapis.com/2.9.0/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script
	src="http://yui.yahooapis.com/2.9.0/build/element/element-min.js"></script>
<!-- Needed for Menus, Buttons and Overlays used in the Toolbar -->
<script
	src="http://yui.yahooapis.com/2.9.0/build/container/container_core-min.js"></script>
<!-- Source file for Rich Text Editor-->
<script
	src="http://yui.yahooapis.com/2.9.0/build/editor/simpleeditor-min.js"></script>
<script type="text/javascript">
	var myEditor = new YAHOO.widget.SimpleEditor('msgpost', {
		height : '300px',
		width : '522px',
		dompath : true,
		handleSubmit : true
	//Turns on the bar at the bottom
	});
	myEditor.render();

	function validate() {
		var title = document.getElementById("title");
		var valid = false;
		var msg = document.getElementById("msgpost");
		if(title.value.length > 0){
			valid = true;
		}else {
			alert("Tittel er påkrevd!");
		}
			
		if(msg.value.length <= 0){
			alert("Innhold påkrevd!");
			valid = false;
		}
		return valid;
	}
</script>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<div id="content">
		<c:if test="${access != null }">
			<form action="ArticleController" method="post">
				<p>Tittel</p>
				<input id="title" type="text" name="title" />
				<div class="yui-skin-sam">
					<textarea name="msgpost" id="msgpost" cols="50" rows="10" >
    
  
</textarea>
					<input type="submit" value="Lagre" onclick="return validate()" />
				</div>
			</form>
		</c:if>
	</div>

	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>