<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<style type="text/css">
	<!--
		.a{margin-left:150px};
	-->
</style>
</head>
<%@include file="user_index.html" %>
<body>
<br>
<p class="a">
	<form action="/ComTest/UploadServlet"  enctype="multipart/form-data" method="post">
		<input type="file" name="filename"><br><br>
		<input type="submit" value="上传">
	</form>
</p>
</body>
</html>