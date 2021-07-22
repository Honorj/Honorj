<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加用户</title>
<link rel="stylesheet" href="admin.css">
</head>
<body class="main">
<form action="/ComTest/Add" method="post" >
<table align="center">
<tr>
<td>学号</td>
<td><input type="text" name="ID"></td>
</tr>
<tr>
<td>姓名</td>
<td><input type="text" name="name"></td>
</tr>
<tr>
<td>性别</td>
<td><input type="text" name="sex"></td>
</tr>
<tr>
<td>地址</td>
<td><input type="text" name="address"></td>
</tr>
<tr>
<td>电话</td>
<td><input type="text" name="phone"></td>
</tr>
<tr>
 <td colspan="2" align="center"><input type="submit" value="增加"></td>
 </tr>
</table>
</form>
<br>
<center><a href="admin_index.html">返回菜单</a></center>
</body>
</html>