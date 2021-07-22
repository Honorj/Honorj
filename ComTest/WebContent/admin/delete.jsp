<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除用户</title>
</head>
<link rel="stylesheet" href="admin.css">
<body class="main">
<form action="/ComTest/Delete" method="post" >
<table align="center">
<tr>
<td>请输入学号</td>
<td><input type="text" name="ID"></td>
</tr>
<tr>
 <td colspan="2" align="center"><input type="submit" value="删除"></td>
 </tr>
</table>
</form>
<br>
<center><a href="admin_index.html">返回菜单</a></center>
</body>
</html>