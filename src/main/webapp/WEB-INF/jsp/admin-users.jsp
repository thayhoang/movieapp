<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<head>

<%@include file="includes/external-links.txt"%>

<script
	src="${pageContext.request.contextPath}/scripts/admin-manage-users.js"></script>

</head>

<body>
	<header>
		<a href="${pageContext.request.contextPath}/admin/movie">Movies</a>
		<a href="${pageContext.request.contextPath}/admin/user">Users</a>
		<div class="logout">
			<a href="${pageContext.request.contextPath}/logout">Log Out</a>
		</div>
	</header>

	<section class="admin">
		<div class="admin_users">
			<h2>Manage users</h2>
			<table class="admin_table">
				<tr>
					<th class="data_col">Username</th>
					<th class="data_col">Password</th>
					<th class="data_col">Role</th>
					<th class="data_col">Full Name</th>
					<th class="data_col">Date Of Birth</th>
					<th class="admin_col">Insert/Delete</th>
				</tr>



				<c:forEach var="user" items="${users}">
					<fmt:formatDate value="${user.dob}" var="formattedDate"
						pattern="dd/MM/yyyy" />
					<tr id='user_${user.id}' class="datarow">
						<td><input class="data" type="text" name="username"
							value="${user.username }"></td>
						<td><input class="data" type="text" name="password"
							value="${user.password }"></td>
						<td><input class="data" type="text" name="role"
							value="${user.role }"></td>
						<td><input class="data" type="text" name="fullName"
							value="${user.fullName }"></td>
						<td><input class="data" maxlength="10" type="text" name="dob"
							value="${formattedDate}"></td>
						<td class="deletecell"><div class="delete hidden"></div></td>
					</tr>
				</c:forEach>
				<tr class="newdatarow">
					<td><input class="newdata" placeholder="username" type="text"
						name="username" value=""></td>
					<td><input class="newdata" placeholder="password" type="text"
						name="password" value=""></td>
					<td><input class="newdata" placeholder="role" type="text"
						name="role" value=""></td>
					<td><input class="newdata" placeholder="full name" type="text"
						name="fullName" value=""></td>
					<td><input class="newdata" placeholder="dd/MM/yyyy"
						type="text" maxlength="10" name="dob" value=""></td>
					<td class="insertcell"><div class="insert hidden"></div></td>
				</tr>
			</table>
		</div>
	</section>


	<%@include file="includes/footer.txt"%>
</body>
</html>