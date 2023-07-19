
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
			<h1>Adding news</h1>
			<p></p>
			<hr>
<label for="title"><b>Title</b></label> <input type="text"
				placeholder="Enter title" name="title" id="title" required>
<label for="brief"><b>Brief news</b></label> <input type="text"
				placeholder="Enter brief" name="brief" id="brief" required>


			<label for="content"><b>Content</b></label> <input type="text"
				placeholder="Enter content" name="content" id="content" required>

			<label for="userId"><b>Email</b></label> <input type="text"
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
