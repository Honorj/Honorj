<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除任务</title>
</head>
<%@include file="taskDAO.jsp" %>
<body>
<br>
<center>
<form action="/ComTest/TaskDel" method="get">
        删除任务<br>
        <input type="text" name="deltask">
        <input type="submit" value="删除">
    </form>
     <br>
    <a href="user_index.html"><h4 color=blue>返回用户首页</h4></a>
</center>
</body>
</html>