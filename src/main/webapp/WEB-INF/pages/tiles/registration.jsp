<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
	<form action="controller" method="post">
	<c:out value="${sessionScope.errorMessage}"/>
		<input type="hidden" name="command" value="do_registration" />
		<div class="container">
			<h1>Registration</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>
<label for="name"><b>Name</b></label> <input type="text"
				placeholder="Enter name" name="name" id="name" required>
<label for="surname"><b>Surname</b></label> <input type="text"
				placeholder="Enter surname" name="surname" id="surname" required>


			<label for="login"><b>Login</b></label> <input type="text"
				placeholder="Enter login" name="login" id="login" required>

			<label for="email"><b>Email</b></label> <input type="text"
				placeholder="Enter Email" name="email" id="email" required>



			<label for="password"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="password" id="password" required>
			<label for="password-repeat"><b>Repeat Password</b></label> <input
				type="password" placeholder="Repeat Password" name="password-repeat"
				id="password-repeat" required>
			<hr>

			<p>
				By creating an account you agree to our <a href="#">Terms &
					Privacy</a>.
			</p>
			<button type="submit" class="registerbtn">Register</button>
		</div>

		<div class="container signin">
			<p>
				Already have an account? <a href="#">Sign in</a>.
			</p>
		</div>
	</form>
</body>
</html>
