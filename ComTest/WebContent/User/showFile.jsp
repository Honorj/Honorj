<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看文件</title>
<%@include file="user_index.html" %>
<style type="text/css">
	<!--
		.a{margin-left:200px;color:orange};
	-->
</style>
</head>
<body><br><br><br>
	<a class="a" href="/ComTest/ShowFileServlet?filename=test.txt">文件在线预览</a><br><br>
	<a class="a" href="/ComTest/ShowFileServlet?filename=3.jpg">图片在线预览</a>
</body>
</html>