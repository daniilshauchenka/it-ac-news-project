<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setBundle basename="localization/bundle"/>

<div class="wrapper">
	<a class="newstitle" href="controller?command=go_to_news_list">News management</a>
	

	<div class="local-link">

		<div align="right">

			<a href="controller?${requestScope['jakarta.servlet.forward.query_string']}&cookieLocale=en">111 <fmt:message key="label.lang.en" /> </a> &nbsp;&nbsp; 
			<a	href="controller?${requestScope['jakarta.servlet.forward.query_string']}&cookieLocale=ru"> 222<fmt:message key="label.lang.ru" /> </a> <br /> <br />
		</div>

		<c:if test="${not (sessionScope.userStatus eq 'active') and not (requestScope.presentation eq 'registration')}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_authorization" /> 
					Enter login: <input type="text" name="login" value="" /><br /> 
					Enter password: <input type="password" name="password" value="" /><br />

					<c:if test="${not (requestScope.AuthenticationError eq null)}">
						<font color="red"> 
						   <c:out value="${requestScope.errorMessage}" />
						</font> 
					</c:if>
						<a href="controller?command=go_to_registration_page">Registration</a>
				
					<input type="submit" value="Sign In" /><br />
				</form>
			</div>

		</c:if>
		
		<c:out value="${requestScope.message}"/>
			<c:out value="${requestScope.errorMessage}"/>
	
		<c:if test="${sessionScope.userStatus eq 'active'}">
		
			<div align="right">
			 <c:out value="You are online, ${userInfo.name}" />
	
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="Sign Out" /><br />
				</form>
			</div>

		</c:if>
	</div>

</div>
