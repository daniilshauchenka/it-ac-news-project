
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<form action="controller" method="post">
		<c:out value="${sessionScope.errorMessage}" />
		<input type="hidden" name="command" value="do_add_news" /> <input
			type="hidden" name="userId" value="${sessionScope.userInfo.id} " />

		<div class="container">
			<h1>Adding news</h1>
			<p></p>
			<hr>
			<label for="title"><b>Title</b></label> <input type="text"
				placeholder="Enter title" name="title" id="title" required>
			<br> 
			<label for="brief"><b>Brief news</b></label> 
			<input
				type="text" placeholder="Enter brief" name="brief" id="brief"
				required> 
			<br> 
			<label for="content"><b>Content</b></label>
			<input type="text" placeholder="Enter content" name="content"
				id="content" required> 
			<br>
			<label for="userId"><b>Image path</b></label> <input type="text" placeholder="Enter Email" name="email"
				id="email" required> <br> 
			<label for="imagePath">Image:</label><input type="file" id="imagePath" name="imagePath"
				accept=".jpg, .png, .jpeg, .gif, .bmp, .tif, .tiff|image/*">
			<br>

			<button type="submit" class="btn">Post</button>
		</div>

		
	</form>
</body>
</html>
