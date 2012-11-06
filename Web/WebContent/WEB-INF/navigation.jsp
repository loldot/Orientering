<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="menucontainer">
	<div class="nav">
		<div class="l"></div>
		<div class="r"></div>
		<ul class="menu">
			<%-- Menu item --%>
			<li><a href="HomeController"> <span class="l"></span> <span
					class="r"></span> <span class="t">Home</span>
			</a></li>
			<li class="menu-separator"></li>
			<li><a href="PersonController"> <span class="l"></span> <span
					class="r"></span> <span class="t">Personer</span>
			</a></li>
			<c:if test="${access != null }">
				<li class="menu-separator"></li>
				<li><a href="#"> <span class="l"></span> <span class="r"></span>
						<span class="t">Brukere</span>
				</a> <%-- Menu subitem --%>
					<ul>
						<li><a href="UserForm">Ny/endre bruker</a></li>
						<%-- <c:if test="${access.user.roleID.levelNumber != 2}">--%>
						<li><a href="UserList">Liste brukere</a></li>
						<%-- 	</c:if> Alle brukere skal ha tilgang til brukerlisten  --%>
					</ul></li>
				<li class="menu-separator"></li>
				<li><a href="#"> <span class="l"></span> <span class="r"></span>
						<span class="t">Aktiviteter</span>
				</a> <%-- Menu subitem --%>
					<ul>
						<c:if test="${access.user.roleID.levelNumber != 2}">
							<li><a href="ActivityForm.jsp">Ny aktivitet</a></li>
						</c:if>
						<li><a href="ActivityList">Liste aktiviteter</a></li>
					</ul></li>
				<c:if test="${access.user.roleID.levelNumber != 2}">
					<li class="menu-separator"></li>
					<li><a href="#"> <span class="l"></span> <span class="r"></span>
							<span class="t">Regnskap</span>
					</a> <%-- Menu subitem --%>
						<ul>
							<li><a href="NewAccountingPost.jsp">Ny post</a></li>
							<li><a href="AccountingList">Se på regnskapet</a></li>
							<li><a href="AccountingDocument">Regnskaps-PDF</a></li>
						</ul></li>
				</c:if>
				<li class="menu-separator"></li>
				<li><a href="#"> <span class="l"></span> <span class="r"></span>
						<span class="t">Materialer</span>
				</a> <%-- Menu subitem --%>
					<ul>
						<c:if test="${access.user.roleID.levelNumber != 2}">
							<li><a href="MaterialManager">Nytt material</a></li>
						</c:if>
						<li><a href="MaterialList">Vise materialer</a></li>
					</ul></li>

			</c:if>
		</ul>
	</div>
</div>