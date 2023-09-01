<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"
          prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date"/>
<c:choose>
	<c:when test="${not(cookie.locale eq null) }">
		<fmt:setBundle basename="localization/bundle_${cookie['locale'].value}"/>
		<fmt:setLocale value="${cookie['locale'].value}"/>
	</c:when>
		<c:when test="${cookie.locale eq null }">
		<fmt:setBundle basename="localization/bundle_en"/>
		<fmt:setLocale value="en_US"/>
	</c:when>
</c:choose>
          
<div class="body-title">
	<a href="controller?command=go_to_base_page"><fmt:message key="button.back" /></a>
</div>
<div class="add-table-margin">
	<c:if test="${action eq 'view'}">
		<c:if test="${sessionScope.role eq 'admin'.toUpperCase()}">
			<div class="first-view-button">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="go_to_edit_news" /> <input
						type="hidden" name="id" id="id" value="${news.idNews}" /> 
						
						<input type="submit" value="Edit" />
				</form>
			</div>

			<div class="second-view-button">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_delete_news" /> <input
						type="hidden" id="id" name="id" value="${news.idNews}" /> <input
						type="submit" value="Delete" />
				</form>
			</div>
		</c:if>

		<table class="news_text_format">
			<tr>
				<td class="space_around_title_text"><fmt:message key="label.view_news.title" /></td>

				<td class="space_around_view_text"><div class="word-breaker">
						<c:out value="${requestScope.news.title }" />
					</div></td>
			</tr>
			<tr>
				<td class="space_around_title_text"><fmt:message key="label.view_news.date" /></td>

				<td class="space_around_view_text"><div class="word-breaker">
				${news.newsDate} <br>
				
					</div></td>
			</tr>
			<tr>
				<td class="space_around_title_text"><fmt:message key="label.view_news.brief" /></td>
				<td class="space_around_view_text"><div class="word-breaker">
						<c:out value="${requestScope.news.briefNews }" />
					</div></td>
			</tr>
			<tr>
				<td class="space_around_title_text"><fmt:message key="label.view_news.content" /></td>
				<td class="space_around_view_text"><div class="word-breaker">
						<c:out value="${requestScope.news.content }" />
					</div></td>
			</tr>
			<tr>
				<td class="space_around_title_text"><fmt:message key="label.view_news.image" /></td>
				<td class="space_around_view_text"><div class="word-breaker">

						<img src="${requestScope.news.imagePath}" width="60%" alt="image">

					</div></td>
			</tr>
			
			<tr>
				<td class="space_around_title_text"><fmt:message key="label.view_news.author" /></td>
				<td class="space_around_view_text"><div class="word-breaker">

<a href="controller?command=go_to_view_user&id=${requestScope.news.authorId}">${requestScope.news.author.name} ${requestScope.news.author.surname} (${requestScope.news.author.login})</a>
						
					</div></td>
			</tr>
		</table>
	</c:if>


	<c:if test="${action eq 'edit'}">
		<form method="post">
			<input type="hidden" name="command" value="do_edit_news" /> <input
				type=hidden name="id" value="${news.idNews}" />

			<table class="news_text_format">
				<tr>
					<td class="space_around_title_text"><fmt:message key="label.view_news.title" /></td>

					<td class="space_around_view_text">
						<div class="word-breaker">
							<input type="text" name="title" id="title"
								value="${news.title }" />
						</div>
					</td>
				</tr>
				<tr>
					<td class="space_around_title_text"><fmt:message key="label.view_news.date" /></td>

					<td class="space_around_view_text"><div class="word-breaker">
						
						</div></td>
						
				</tr>
				<tr>
					<td class="space_around_title_text"><fmt:message key="label.view_news.brief" /></td>
					<td class="space_around_view_text">
						<div class="word-breaker">
							<input type="text" name="brief" id="brief"
								value="${news.briefNews }" />
						</div>
					</td>
				</tr>
				<tr>
					<td class="space_around_title_text"><fmt:message key="label.view_news.content" /></td>
					<td class="space_around_view_text"><div class="word-breaker">
							<div class="word-breaker">
								<input type="text" name="content" id="content"
									value="${news.content }" />
							</div>
						</div></td>
				</tr>
				<tr>
					<td class="space_around_title_text"><fmt:message key="label.view_news.image" /></td>
					<td class="space_around_view_text"><div class="word-breaker">

							<img src="${news.imagePath}" width="60%" alt="image">
							<div class="word-breaker">
								<input type="text" name="imagePath" id="imagePath"
									value="${news.imagePath }" />
							</div>
						</div></td>
				</tr>
			</table>
			
			<input type="submit" value="<fmt:message key="button.save" />" />
			
			
			<a href="controller?command=go_to_view_news&id=${news.idNews}"><fmt:message key="button.cancel" /></a>
		
		</form>
	</c:if>
</div>

