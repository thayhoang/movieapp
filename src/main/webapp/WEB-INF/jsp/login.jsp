<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<style type="text/css">
* {
	font-family: Arial, Verdana, sans-serif;
	color: #665544;
}


label {
	margin-left: 5px;
}

input {
	border-bottom: 1px dotted #dcdcdc;
	border-top: none;
	border-right: none;
	border-left: none;
	padding: 5px;
	width: 250px;
	margin-bottom: 20px;
}

input:focus {
	border: 1px dotted #dcdcdc;
	outline: none;
}

input#submit {
	color: #ffffff;
	background-color: #665544;
	border: none;
	border-radius: 5px;
	width: 80px;
}

input#submit:hover {
	color: #665544;
	background-color: #efefef;
}

fieldset {
	width: 350px;
	border: 1px solid #dcdcdc;
	border-radius: 10px;
	padding: 20px;
	text-align: right;
}

legend {
	background-color: #efefef;
	border: 1px solid #dcdcdc;
	border-radius: 10px;
	padding: 10px 20px;
	text-align: left;
	text-transform: uppercase;
}

.formlogin {
	width: 500px;
	margin: 20px auto;
}
</style>
</head>
<body class="loginpage">
	<form class="formlogin"
		action="${pageContext.request.contextPath}/login" method="post">
		<fieldset>
			<legend>Sign In</legend>
			<label for="username">Username: </label><input type="text"
				id="username" name="username" /> <label for="password">Password:
			</label><input type="password" id="password" name="password" /> <input
				type="submit" value="Sign In" id="submit" />
		</fieldset>
	</form>
</body>
</html>