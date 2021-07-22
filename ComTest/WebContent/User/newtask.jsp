<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建任务</title>
</head>
<%@include file="taskDAO.jsp" %>
<body>
<div align="center">
    <form action="/ComTest/TaskAdd" method="post">
        添加任务<br /><br />
   任务名称<input type="text" name="task">
      任务完成度  <input type="text" name="newtask">
   截至时间 <input type="text" name="Time"><br><br>
        <input type="submit" value="添加">
        <h5 color="red">任务完成度为0或者1（0表示未完成，1表示已完成）</h5><br>
        <a href="user_index.html"><h4 color=blue>返回用户首页</h4></a>
    </form>
</body>
</html>