<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="body-title">
	<a href="controller?command=go_to_news_list">News >> </a> Users List
</div>

<c:if test="${sessionScope.role eq 'admin'.toUpperCase()}">
	<form action="" method="post">
		<c:forEach var="user" items="${requestScope.usersList}">
			<div class="single-news-wrapper">
				<div class="single-news-header-wrapper">
					<div class="news-title">
						<a href="controller?command=go_to_view_user&id=${user.id}"><c:out
								value="${user.login}" /></a>
					</div>
				</div>
			</div>
		</c:forEach>

		<table border="1">
			<tr>
				<c:forEach begin="1" end="${totalPages}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td><a href="controller?command=go_to_users_list&page=${i}">${i}</a></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</table>

		<div class="no-news">
			<c:if test="${requestScope.usersList eq null}">
        <fmt:message key="label.news_list.no_users" />"
	</c:if>
		</div>
	</form>


</c:if>
