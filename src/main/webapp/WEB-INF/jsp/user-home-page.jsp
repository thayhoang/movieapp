<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<%@include file="includes/external-links.txt"%>

</head>

<body>
	<%@include file="includes/header.txt"%>

	<div style="margin-top: 20px; margin-left: 450px;">
		<a href="${pageContext.request.contextPath}/user/movies"><img
			alt="User Page"
			src="${pageContext.request.contextPath}/images/movies.jpg"
			height="200" width="200"></a>
	</div>

	<%@include file="includes/footer.txt"%>
</body>
</html>