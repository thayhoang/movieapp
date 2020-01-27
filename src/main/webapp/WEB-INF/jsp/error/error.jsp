<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isErrorPage="true"%>
<html>
<head>
<title>Error Page</title>
<style type="text/css">
.error {
	text-align: center;
}

.thebiglebowski {
	width: 720px;
	height: 406px;
	margin: 20px auto;
	display: block;
}

body {
	/* background-color: #a9a9a9; */
	background-color: #d4d4d4;
}
</style>
</head>
<body>
	<img class="thebiglebowski" alt="The Big Lebowski"
		src="${pageContext.request.contextPath}/images/thebiglebowski.jpg">
	<div class="error">
		<h1>Ooop! Some kind of error has occurred</h1>
	</div>
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	
	<table border="1">
		<tr valign="top">
			<td width="30%"><b>Error:</b></td>
			<td>${pageContext.exception}</td>
		</tr>
		<tr valign="top">
			<td><b>URI:</b></td>
			<td>${pageContext.errorData.requestURI}</td>
		</tr>
		<tr valign="top">
			<td><b>Status code:</b></td>
			<td>${pageContext.errorData.statusCode}</td>
		</tr>
		<tr valign="top">
			<td><b>Stack trace:</b></td>
			<td><c:forEach var="trace"
					items="${pageContext.exception.stackTrace}">
					<p>${trace}</p>
				</c:forEach></td>
		</tr>
	</table>
</body>
</html>