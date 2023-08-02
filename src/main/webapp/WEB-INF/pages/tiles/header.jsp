<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
	<c:when test="${not(cookie.locale eq null) }">
		<fmt:setBundle
			basename="localization/bundle_${cookie['locale'].value}" />
		<fmt:setLocale value="${cookie['locale'].value}" />

	</c:when>
	<c:when test="${cookie.locale eq null }">
		<fmt:setBundle basename="localization/bundle_en" />
		<fmt:setLocale value="en" />

	</c:when>
</c:choose>



<div class="wrapper">
	<a class="newstitle" href="controller?command=go_to_news_list"><fmt:message
			key="label.header.project_title" /></a>


	<div class="local-link">

		<div align="right" >
			<form action="controller" method="get"
				style="display: inline-block; margin: 0">
				<input type="hidden" name="command" value=change_locale> <input
					type="hidden" name="cookieLocale" value="en"> <input
					type="submit" value="<fmt:message key="label.lang.en"/>">
			</form>
			<form action="controller" method="get" style="display: inline-block">
				<input type="hidden" name="command" value="change_locale"> <input
					type="hidden" name="cookieLocale" value="ru"> <input
					type="submit" value="<fmt:message key="label.lang.ru"/>">
			</form>
			<br>
	
			


			<c:if
				test="${not (sessionScope.userStatus eq 'active') and not (requestScope.presentation eq 'registration')}">
				
					<form action="controller" method="post">
						<input type="hidden" name="command" value="do_authorization" />
						<fmt:message key="label.auth.enter_login" />
						<input type="text" name="login" value="" /><br />
						<fmt:message key="label.auth.enter_password" />
						<input type="password" name="password" value="" /><br />

						
						<a href="controller?command=go_to_registration_page"><fmt:message
								key="button.auth.registration" /></a> <input type="submit"
							value="<fmt:message key="button.auth.sign_in"/>" /><br />
					</form>
						<font color="red"> 
							<c:out value="${requestScope.message}" />
							<c:out value="${requestScope.errorMessage}" />
					</font>
			</c:if>

			
			<c:if test="${sessionScope.userStatus eq 'active'}">

				<p>
					<fmt:message key="label.you_are_online" />
					<a href="controller?command=go_to_user_profile"><c:out
							value=" ${userInfo.name}" /></a>
				</p>
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" /> <input
						type="submit" value="<fmt:message key="button.auth.sign_out"/>" /><br />
				</form>

			</c:if>
		</div>
	</div>

</div>
