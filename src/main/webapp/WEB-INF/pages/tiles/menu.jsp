<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">Menu for role ${sessionScope.role }</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<ul style="list-style-image: url(images/img.jpg); text-align: left;">
				<li style="padding-left: 15px;">
				<a href="controller?command=go_to_news_list">Latest news</a><br />
				</li>
				
				<li style="padding-left: 15px;">
					<a href="controller?command=go_to_search_news">Search news</a><br />
				</li>
					
				<li style="padding-left: 15px;">
					<a href="controller?command=go_to_user_profile">Profile </a>
				</li>
				
				<c:if test="${sessionScope.role eq 'ADMIN'}">
				<p>Admin functions</p>
					<li style="padding-left: 15px;">
						<a href="">add news </a>
						
					</li>
					<li style="padding-left: 15px;">
						
						<a href="">users list</a> <br />
					</li>
				</c:if>
	
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<!--  grey free space at the bottom of menu -->
	<div style="height: 25px;"></div>
</div>

