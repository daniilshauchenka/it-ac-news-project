<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
	<form action="${pageContext.request.contextPath}/Controller" method="post">
		<input type="hidden" name="command" value="registration" />
		<div class="container">
			<h1>Registration</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>

			<label for="email"><b>Email</b></label> <input type="text"
				placeholder="Enter Email" name="email" id="email" required>

			<label for="psw"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="psw" id="psw" required> <label
				for="psw-repeat"><b>Repeat Password</b></label> <input
				type="password" placeholder="Repeat Password" name="psw-repeat"
				id="psw-repeat" required>
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
