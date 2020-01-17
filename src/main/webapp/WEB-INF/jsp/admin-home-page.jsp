<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE>
<html>
<head>

<%@include file="includes/external-links.txt"%>

</head>

<body>
	<%@include file="includes/header.txt"%>


	<div style="margin-top: 20; margin-left: 450;">
		<a href="${pageContext.request.contextPath}/admin/manageusers"><img
			alt="Manage Users Page"
			src="${pageContext.request.contextPath}/images/manageUsers.png"
			height="200" width="200"></a> <a
			href="${pageContext.request.contextPath}/managemovies"><img
			alt="Manage Movies Page"
			src="${pageContext.request.contextPath}/images/manageMovies.jpg"
			height="200" width="200"></a>
	</div>

	<%@include file="includes/footer.txt"%>
</body>
</html>