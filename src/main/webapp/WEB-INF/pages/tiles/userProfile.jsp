<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/style.css">
<title>Users profile</title>
</head>
<body>
<div class=container>
	<c:if test="${action eq 'view'}">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_edit_user_profile" /> <input
				type="hidden" name="id" value="${news.idNews}" /> <input
				type="submit" value="Edit" />
		</form>
		<table>
			<tbody>
				<tr>
					<td>Name:</td>
					<td>${userInfo.name}</td>
				</tr>
				<tr>
					<td>Surname:</td>
					<td>${userInfo.surname}</td>
				</tr>
				<tr>
					<td>Email:</td>
					<td>${userInfo.email}</td>
				</tr>
				<tr>
					<td>Login:</td>
					<td>${userInfo.login}</td>
				</tr>
			</tbody>
		</table>
	</c:if>
	<c:if test="${action eq 'edit'}">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_update_user" />

	<table>
				<tbody>
					<tr>
						<td>Name:</td>
						<td><input type="text" value="${userInfo.name}"
							placeholder="Enter name" name="name" id="name"></td>
					</tr>
					<tr>
						<td>Surname:</td>
						<td><input type="text" value="${userInfo.surname}"
							placeholder="Enter surname" name="surname" id="surname">
						</td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" value="${userInfo.email}"
							placeholder="Enter email" name="email" id="email"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" value="${userInfo.login}"
							placeholder="Enter login" name="login" id="login"></td>
					</tr>
					<tr>
						<td>Previous password:</td>
						<td><input type="password" value=""
							placeholder="Enter previous password" name="prevPassword" id="prevPassword"></td>
					</tr>
					<tr>
						<td>New password:</td>
						<td><input type="password" value=""
							placeholder="Enter new password" name="newPassword" id="newPassword"></td>
					</tr>
						<tr>
						<td>Repeat new password:</td>
						<td><input type="password" value=""
							placeholder="Enter new password" name="repeatedPassword" id="repeatedPassword"></td>
					</tr>
					
					
						
				</tbody>
			
			</table>
			<button type="submit" class="savebtn">Save</button>
		
				
		
	

		</form>

	</c:if>
</div>
		


</body>
</html>