
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
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
</head>
<body>
	<a href="controller?command=go_to_base_page"> <fmt:message
			key="button.back" /></a>
	<form action="controller" method="post">
		<c:out value="${sessionScope.errorMessage}" />
		<input type="hidden" name="command" value="do_add_news" /> <input
			type="hidden" name="userId" value="${sessionScope.userInfo.id} " />

		<div class="container">
			<h1>
				<fmt:message key="label.add_news.page_title" />
			</h1>
			<table style="width:100%">
			    <colgroup>
     			 	<col span="1" style="width: 20%;">
       				<col span="1" style="width: 80%;">
     
    </colgroup>
				<tbody>
					<tr>
						<td><label for="title"><b><fmt:message
										key="label.add_news.title" /></b></label></td>
						<td><input type="text" placeholder="" name="title" id="title"
							style="width: 100%" required></td>
					</tr>
					<tr>
						<td><label for="brief"><b><fmt:message
										key="label.add_news.brief" /></b></label></td>
						<td><input type="text" placeholder="" name="brief" id="brief"
							style="width: 100%" required></td>
					</tr>
					<tr>
						<td><label for="content"><b><fmt:message
										key="label.add_news.content" /></b></label></td>
						<td><input type="text" placeholder="" name="content"
							id="content" style="width: 100%" required></td>
					</tr>
					<tr>
						<td><label for="image_path"><b><fmt:message
										key="label.add_news.image_path" /></b></label></td>
						<td><input type="text" placeholder="" name="image_path"
							id="image_path" style="width: 100%" required></td>
					</tr>
					
				</tbody>
			</table>
<br>

			<button type="submit" style="position: center"><fmt:message	key="button.add_news.post" /></button>
		</div>


	</form>
</body>
</html>
