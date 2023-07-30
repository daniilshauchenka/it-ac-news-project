<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	<a href="controller?command=go_to_base_page">News >> </a> View News
</div>

<div class="add-table-margin">

<c:if test="${sessionScope.role eq 'admin'.toUpperCase()}">
	
	<div class="second-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="do_ban_user" /> 
			<input type="hidden" id="id" name="id" value="${user.id}" /> 
			<input type="submit" value="Ban" />
		</form>
	</div>
</c:if>

	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">User login</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.user.login }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">User name</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.user.name} ${requestScope.user.surname} " />
				</div></td>
		</tr>
	
		<tr>
			<td class="space_around_title_text">Email</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.user.email}" />
				</div></td>
		</tr>
		
	</table>
</div>

