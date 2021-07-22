<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<style type="text/css">
	.divForm{
		position:absolute;
		width:300px;
		height:200px;
		border:1px solid;
		text-align:center;
		margin-top:50px;
		margin-left:300px;
		background-color:#cccfff;
	}
</style>
<body background="download_file/4.jpg">
 <div class="divForm">
  <fieldset>
    <legend align="center">用户登录</legend>	
    <!-- 表单数据的提交方式为POST -->
    <form action="/ComTest/Login" method="post">
                <p>用户名:
                    <!-- 1.文本输入框控件 -->
                    <input type="text" name="username" id="username"/><br>
                </p>
                <p>密&nbsp;&nbsp;码:
                <!-- 2.密码输入框控件 -->
               <input type="password" name="password"  id="password"/><br>
                </p>
           
                <p>
             		   登录类型:
                    <input type="radio" name="mold" value="user" /> 用户
                    <input type="radio" name="mold" value="admin" />管理员 
                </p>
                
                <p>
                    <!-- 6.提交按钮控件 -->
                    <input type="submit" value="登录" />  
                    <!-- 7.重置按钮控件，单击后会清空当前form -->
                    <input type="reset" value="重填" />    
                </p>
    </form>
   </fieldset>
</div>
</body>
</html>