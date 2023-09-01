<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
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
	
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/style.css">

</head>
<body>
<a href="controller?command=go_to_base_page"> <fmt:message
			key="button.back" /></a> <br>
	<form action="controller" method="post">
	<c:out value="${sessionScope.errorMessage}"/>
		<input type="hidden" name="command" value="do_registration" />
		<div class="container">
			<h1><fmt:message key="label.registration.page_title"/></h1>
			<p><fmt:message key="label.registration.caption"/></p>
			<hr>
			
				<table style="width:100%">
			    <colgroup>
     			 	<col span="1" style="width: 20%;">
       				<col span="1" style="width: 80%;">
     
   				</colgroup>
					
			<tbody>
				<tbody>
					<tr>
						<td><fmt:message key="label.registration.name" /></td>
						<td><input type="text" value="${userInfo.name}"
							placeholder="" name="name" id="name"></td>
					</tr>
					<tr>
						<td><fmt:message key="label.registration.surname" /></td>
						<td><input type="text" value="${userInfo.surname}"
							placeholder="" name="surname" id="surname">
						</td>
					</tr>
					<tr>
						<td><fmt:message key="label.registration.email" /></td>
						<td><input type="text" value="${userInfo.email}"
							placeholder="" name="email" id="email"></td>
					</tr>
					<tr>
						<td><fmt:message key="label.registration.login" /></td>
						<td><input type="text" value="${userInfo.login}"
							placeholder="" name="login" id="login"></td>
					</tr>
					<tr>
						<td><fmt:message key="label.registration.password" /></td>
						<td><input type="password" value=""
							placeholder="" name="password" id="password"></td>
					</tr>
					<tr>
						<td><fmt:message key="label.registration.repeat_password" /></td>
						<td><input type="password" value=""
							placeholder="" name="password-repeat" id="password-repeat"></td>
					</tr>
		
				</tbody>		
			</table>
			
		
			<button type="submit" class="registerbtn"><fmt:message key="button.registration.register" /></button>
		</div>

		
	</form>
</body>
</html>
