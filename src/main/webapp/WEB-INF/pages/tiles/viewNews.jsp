<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	<a href="controller?command=go_to_base_page">News >> </a> View News
</div>

<div class="add-table-margin">

<c:if test="${sessionScope.role eq 'admin'.toUpperCase()}">
	<div class="first-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="edit" /> <input
				type="hidden" name="id" value="${news.idNews}" /> <input
				type="submit" value="Edit" />
		</form>
	</div>

	<div class="second-view-button">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="delete" /> <input
				type="hidden" name="id" value="${news.idNews}" /> <input
				type="submit" value="Delete" />
		</form>
	</div>
</c:if>

	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">News Title</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">News Date</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.newsDate }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">Brief</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.briefNews }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">Content</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.content }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">Image</td>
			<td class="space_around_view_text"><div class="word-breaker">
			<p>${requestScope.news.imagePath }</p>
			<img src="${requestScope.news.imagePath}" width="60%" alt = "image">
			
						</div></td>
		</tr>
	</table>
</div>

