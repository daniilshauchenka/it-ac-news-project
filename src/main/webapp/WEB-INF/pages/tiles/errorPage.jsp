 <%@page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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


</body>