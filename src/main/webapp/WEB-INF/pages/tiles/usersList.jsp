<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="body-title">
	<a href="controller?command=go_to_news_list">News >> </a> Users List
</div>

<c:if test="${sessionScope.role eq 'admin'.toUpperCase()}">
						      <a href="">ban</a> 
			
<form action="" method="post">
	<c:forEach var="user" items="${requestScope.usersList}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<a href="controller?command=go_to_view_user&id=${user.id}"><c:out value="${user.login} " /></a> 
				</div>

				<div class="news-link-to-wrapper">
					<div class="link-position">
						      <a href="">ban</a> 
					
					</div>
				</div>
			</div>
		</div>

	</c:forEach>

	<div class="no-news">
		<c:if test="${requestScope.usersList eq null}">
        No users.
	</c:if>
	</div>
</form>

</c:if>
