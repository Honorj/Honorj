<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载</title>
<style type="text/css">
	<!--
		.a{margin-left:200px;color:orange};
	-->
</style>
</head>
<%@include file="user_index.html" %>
<body>
<br><br>
	<a class="a" href="/ComTest/DownloadServlet?filename=test.txt">文件下载</a><br><br>
	<a class="a" href="/ComTest/DownloadServlet?filename=3.jpg">图片下载</a>
</body>
</html>