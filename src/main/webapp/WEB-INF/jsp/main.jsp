<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="by.tr.conspect.bean.User" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		User userX = (User) request.getAttribute("user");
		if (userX == null) {
			userX = new User();
		}
		out.println(userX.getName());
	%>
	<br />
	<%
		out.println(request.getParameter("login"));
	%>

	<jsp:useBean id="user" class="by.tr.conspect.bean.User" scope="request" />

	<jsp:getProperty property="name" name="user" />

</body>
</html>