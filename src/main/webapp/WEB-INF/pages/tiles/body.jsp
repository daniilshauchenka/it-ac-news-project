<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${requestScope.presentation eq null }">
	<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
</c:if>


	<c:import url="/WEB-INF/pages/tiles/${requestScope.presentation}.jsp" />


