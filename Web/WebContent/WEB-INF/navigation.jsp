<%@page import="no.orientering.utils.BtFeedReader"%>
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
			<li><a href="<%=BtFeedReader.getLatestNews() %>">Siste nytt fra bt.no!</a></li>
			<c:if test="${access != null }">

				<li class="menu-separator"></li>
				<li><a href="PersonController"> <span class="l"></span> <span
						class="r"></span> <span class="t">Personer</span>
				</a></li>
				<li class="menu-separator"></li>
				<li><a href="UserController"> <span class="l"></span> <span
						class="r"></span> <span class="t">Endre brukerdata</span>
				</a></li>

				<li class="menu-separator"></li>
				<li><a href="HomeController"> <span class="l"></span> <span
						class="r"></span> <span class="t">Artikler</span>
				</a> <%-- Menu subitem --%>
					<ul>
						<li><a href="HomeController?action=new">Ny artikkel</a></li>
					</ul></li>



			</c:if>
		</ul>
	</div>
</div>