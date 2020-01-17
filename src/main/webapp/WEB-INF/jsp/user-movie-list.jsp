<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="includes/external-links.txt"%>


<script
	src="${pageContext.request.contextPath}/scripts/user-add-favs.js"></script>


</head>
<body>
	<%@include file="includes/header.txt"%>
	<%@include file="includes/favs-list.txt"%>


	<section class="movie_list">
		<h2>
			<c:out value="Hi, ${user.fullName }" />
		</h2>
		<p class="welcome">Here are some movies you might like. Click on
			the heart icon to add them to your favorites list.</p>
		<ul class="non_favs">
			<c:forEach var="nonfav" items="${nonfavs}">
				<li id="fav_${nonfav.id}">
					<figure>
						<a
							href="${pageContext.request.contextPath}/user/movies?id=${nonfav.id}"><img
							class="thumbnail" alt="Thumbnail"
							src="${pageContext.request.contextPath}/images-movies/${nonfav.id}-tn.jpg"></a>
						<figcaption>
							<%-- <h3>
								<a
									href="${pageContext.request.contextPath}/user/movies?id=${nonfav.id}"><c:out
										value="${nonfav.title }" /></a>
							</h3> --%>
							<div class="title">
								<a
									href="${pageContext.request.contextPath}/user/movies?id=${nonfav.id}"><c:out
										value="${nonfav.title }" /></a>
							</div>
							<div class="description">
								<c:out value="${nonfav.description }" />
							</div>
							<div class="add"></div>


						</figcaption>
					</figure>
				</li>
			</c:forEach>
		</ul>
	</section>

	<div class='loader_large hidden'></div>


	<%@include file="includes/footer.txt"%>
</body>
</html>