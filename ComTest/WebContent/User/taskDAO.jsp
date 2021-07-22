<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
 <%@page import="DAO.TaskDAO"%>
 <%@page import="bean.TaskBean"%>
 <%@page import="java.util.ArrayList"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务管理</title>
<style type="text/css">
  body{
  background:url("../download_file/3.jpg");
  background-repeat:no-repeat;
  background-size:1310px 720px;
  background-position:
  right top,
  left top;
  
  } 
  </style>
</head>
<body>
<% TaskDAO taskdao = new TaskDAO(); %>
<h2 align="center">任务管理中心</h2>
<hr/><br/>
<div>
</div>
    <form action="/ComTest/TaskUpdate" method="post">
<table border="1" align="center">
    <th>未完成任务</th>
   <th>截止日期</th>
    <th>已完成任务</th>
    <ul>
        <tr>
                <td>
                    <%
                 ArrayList<TaskBean> list1 = taskdao.findAllundo();
		       for (int i = 0; i < list1.size(); i++) {
		    	   %>
		    	   <input type="checkbox" name="checkbox" value=<%=list1.get(i).getName() %> >
		    	   <%
               out.print(list1.get(i).getName()+"<br />");
               } %>
                </td>
                <td>
                 <%
                 ArrayList<TaskBean> list2 = taskdao.findAllundo();
		       for (int i = 0; i < list2.size(); i++) {
               out.print(list2.get(i).getTime()+"<br />");
               } %></td>
                
                <td>
                 <%
                 ArrayList<TaskBean> list = taskdao.findAlldo();
		       for (int i = 0; i < list.size(); i++) {
               out.print(list.get(i).getName()+"<br />");
               } %></td>
               
        </tr>
    </ul>
    <tr>
        <td colspan="4" align="center">
            <input type="submit" value="完成任务">
        </td>
    </tr>
</table>
    </form>
</body>
</html>