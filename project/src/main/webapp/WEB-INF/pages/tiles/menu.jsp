<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
	<c:when test="${not(cookie.locale eq null) }">
		<fmt:setBundle basename="localization/bundle_${cookie['locale'].value}"/>
	</c:when>
		<c:when test="${cookie.locale eq null }">
		<fmt:setBundle basename="localization/bundle_en"/>
	</c:when>
</c:choose>

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title"><fmt:message key="label.menu.title"/></div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<ul style="list-style-image: url(images/img.jpg); text-align: left;">
				<li style="padding-left: 15px;">
				<a href="controller?command=go_to_news_list"><fmt:message key="label.menu.latest_news"/></a><br />
				</li>
				
				<li style="padding-left: 15px;">
					<a href="controller?command=go_to_search_news"><fmt:message key="label.menu.search_news"/></a><br />
				</li>
					
				<li style="padding-left: 15px;">
					<a href="controller?command=go_to_user_profile"><fmt:message key="label.menu.profile"/> </a>
				</li>
				
				<c:if test="${sessionScope.role eq 'ADMIN'}">
				
					<li style="padding-left: 15px;">
						<a href="controller?command=go_to_add_news"><fmt:message key="label.menu.add_news"/></a>
						
					</li>
					<li style="padding-left: 15px;">
						
						<a href="controller?command=go_to_users_list&page=1"><fmt:message key="label.menu.users_list"/></a> <br />
					</li>
				</c:if>
	
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	
	<div style="height: 25px;"></div>
</div>

