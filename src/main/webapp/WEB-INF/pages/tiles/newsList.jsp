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



<form action="" method="post">
	<c:forEach var="news" items="${requestScope.newsList}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<a href="controller?command=go_to_view_news&id=${news.idNews}"><c:out value="${news.title}" /></a> 		
				</div>
				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
						
				
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
						      <a href="">editlink </a> 
						</c:if>
						
					
					    
   					    <c:if test="${sessionScope.role eq 'admin'}">
   					         <input type="checkbox" name="idNews" value="${news.idNews }" />
   					    </c:if>
					</div>
				</div>

			</div>
		</div>

	</c:forEach>





	<div class="no-news">
		<c:if test="${requestScope.newsList eq null}">
      <fmt:message key="label.news_list.no_news" />"
	</c:if>
	</div>
</form>
