<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles/style.css">
<title>Insert title here</title>
</head>
<body>

	<%
	String eMes = (String) request.getAttribute("error");

	if (eMes != null) {
	%>
	<font color="red"> <%
 out.println(eMes);
 }
 %>

	</font>

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="authorization" /> Enter login:<br />
		<input type="text" name="login" value="" /><br /> Enter password:<br />
		<input type="password" name="password" value="" /><br /> <input
			type="submit" value="Отправить" /><br />
	</form>

	<a href="WEB-INF/jsp/registration.jsp">Registration</a>

command=go_to_registration
</body>
</html>