 <%@page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
	<c:when test="${not(cookie.locale eq null) }">
		<fmt:setBundle basename="localization/bundle_${cookie['locale'].value}"/>
	</c:when>
		<c:when test="${cookie.locale eq null }">
		<fmt:setBundle basename="localization/bundle_en"/>
	</c:when>
</c:choose>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="styles/style.css">
<title>Error</title>
</head>
<body>
<h1>Технические шоколадки. Приносим свои извинения.</h1>
<c:out value="${message }"></c:out>
<c:out value="${errorMessage }"></c:out>

<a href="controller?command=go_to_base_page"><fmt:message key="label.error.go_to_base_page"/></a>

</body>