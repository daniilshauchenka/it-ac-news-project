<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>


<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">

</head>

<body>		
	<ul>
<c:forEach var="items" items="${requestScope}">
<li>${items.key} -> ${items.value}</li>
</c:forEach>
</ul>

</body>


