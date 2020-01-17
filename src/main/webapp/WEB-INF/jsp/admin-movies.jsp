<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>

<%@include file="includes/external-links.txt"%>

<script
	src="${pageContext.request.contextPath}/scripts/admin-manage-movies.js"></script>


</head>
<body>
	<%@include file="includes/header.txt"%>

	<section class="admin">
		<div class="admin_movies">
			<h2>Manage movies</h2>
			<table class="admin_table">
				<tr>
					<th class="data_col">Title</th>
					<th class="data_col description">Description</th>
					<th class="data_col trailer">Trailer</th>
					<th class="admin_col">Insert/Delete</th>
				</tr>

				<c:forEach var="movie" items="${movies }">
					<tr id='movie_${movie.id}' class="datarow">
						<td><input class="data" type="text" name="title"
							value="${movie.title }"></td>
						<td><input class="data description" type="text"
							name="description" value="${movie.description }"></td>
						<td><input class="data trailer" type="text" name="trailer"
							value="${movie.trailer }"></td>
						<td class="deletecell"><div class="delete hidden"></div></td>
					</tr>
				</c:forEach>

				<tr class='newdatarow'>
					<td><input class='newdata' type='text' name='title' value=''></td>
					<td><input class='newdata description' type='text'
						name='description' value=''></td>
					<td><input class='newdata' type='text' name='trailer' value=''></td>
					<td class='insertcell'><div class='insert'></div></td>
				</tr>
			</table>
		</div>
	</section>
	<%@include file="includes/footer.txt"%>
</body>
</html>