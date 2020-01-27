<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="includes/external-links.txt"%>

<script
	src="${pageContext.request.contextPath}/scripts/user-add-remove-favs-single.js"></script>


</head>
<body>
	<header>
		<a href="${pageContext.request.contextPath}/app">Home</a>
		<div class="logout">
			<a href="${pageContext.request.contextPath}/logout">Log Out</a>
		</div>
	</header>


	<%@include file="includes/favs-list.txt"%>

	<c:choose>
		<c:when test="${isfav}">
			<c:set var="testfav" value="Remove from favorites" />
			<c:set var="action" value="remove" />
		</c:when>
		<c:when test="${!isfav}">
			<c:set var="testfav" value="Add to favorites" />
			<c:set var="action" value="add" />
		</c:when>
	</c:choose>

	<section class="movie_single" id="single_${movie.id}">
		<iframe class="movie_player" src="${movie.trailer}"></iframe>
		<h3 class="title">
			<c:out value="${movie.title }" />
		</h3>
		<div class="actions">
			<div id="single_${movie.id}" class="add_remove ${action}">
				<p>${testfav }</p>
			</div>
		</div>
		<p class="description">
			<c:out value="${movie.description }" />
		</p>
	</section>

	<div class='loader_large hidden'></div>

	<%@include file="includes/footer.txt"%>
</body>
</html>