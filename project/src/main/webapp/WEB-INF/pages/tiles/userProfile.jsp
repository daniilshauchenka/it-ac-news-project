<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
	<c:when test="${not(cookie.locale eq null) }">
		<fmt:setBundle
			basename="localization/bundle_${cookie['locale'].value}" />
	</c:when>
	<c:when test="${cookie.locale eq null }">
		<fmt:setBundle basename="localization/bundle_en" />
	</c:when>
</c:choose>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/style.css">
<title><fmt:message key="label.user_profile.page_title" /></title>
</head>
<body>
<div class=container>
<a href="controller?command=go_to_base_page"> <fmt:message
			key="button.back" /></a> <br>
	<c:if test="${action eq 'view'}">
	
			<table style="width:100%">
			    <colgroup>
     			 	<col span="1" style="width: 20%;">
       				<col span="1" style="width: 80%;">
     
   				</colgroup>
					
			<tbody>
				<tr>
					<td><fmt:message key="label.user_profile.name" /></td>
					<td>${userInfo.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="label.user_profile.surname" /></td>
					<td>${userInfo.surname}</td>
				</tr>
				<tr>
					<td><fmt:message key="label.user_profile.email" /></td>
					<td>${userInfo.email}</td>
				</tr>
				<tr>
					<td><fmt:message key="label.user_profile.login" /></td>
					<td>${userInfo.login}</td>
				</tr>
			</tbody>
		</table>
			<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_edit_user_profile" /> <input
				type="hidden" name="id" value="${news.idNews}" /> <input
				type="submit" value="Edit" />
		</form>
	</c:if>
	<c:if test="${action eq 'edit'}">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_update_user" />

	<table style="width:100%">
			    <colgroup>
     			 	<col span="1" style="width: 20%;">
       				<col span="1" style="width: 80%;">
     
   				</colgroup>
					
			<tbody>
				<tbody>
					<tr>
						<td><fmt:message key="label.user_profile.name" /></td>
						<td><input type="text" value="${userInfo.name}"
							placeholder="" name="name" id="name"></td>
					</tr>
					<tr>
						<td><fmt:message key="label.user_profile.surname" /></td>
						<td><input type="text" value="${userInfo.surname}"
							placeholder="" name="surname" id="surname">
						</td>
					</tr>
					<tr>
						<td><fmt:message key="label.user_profile.email" /></td>
						<td><input type="text" value="${userInfo.email}"
							placeholder="" name="email" id="email"></td>
					</tr>
					<tr>
						<td><fmt:message key="label.user_profile.login" /></td>
						<td><input type="text" value="${userInfo.login}"
							placeholder="" name="login" id="login"></td>
					</tr>
					<tr>
						<td><fmt:message key="label.user_profile.previous_password" /></td>
						<td><input type="password" value=""
							placeholder="" name="prevPassword" id="prevPassword"></td>
					</tr>
					<tr>
						<td><fmt:message key="label.user_profile.new_password" /></td>
						<td><input type="password" value=""
							placeholder="" name="newPassword" id="newPassword"></td>
					</tr>
						<tr>
						<td><fmt:message key="label.user_profile.repeat_new_password" /></td>
						<td><input type="password" value=""
							placeholder="" name="repeatedPassword" id="repeatedPassword"></td>
					</tr>
					
					
						
				</tbody>
			
			</table>
			<button type="submit" class="savebtn"><fmt:message key="button.save" /></button>
		
				
		
	

		</form>

	</c:if>
</div>
		


</body>
</html>