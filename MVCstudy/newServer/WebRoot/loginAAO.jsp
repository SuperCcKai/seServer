<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="_utils._spider.LoginMess" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>登录教务处</title>
</head>
<body>
	<% LoginMess.callKeepCookieServlet(); %>
	<% LoginMess.callLoginMessServlet(); %>
	<% Thread.currentThread().sleep(2000); %>
	<form action="http://localhost:8080/newServer/login" method="post">
		学号：<input type="text" name="WebUserNO" id="WebUserNO" />
		<br/>
		密码：<input type="password" name="Password" id="Password" />
		<br/>
		验证码：<input type="text" name="Agnomen" id="Agnomen" />
		<img src="_resource/img/checkCode.jpg" />
		<br/>
		<input type="submit" />
	</form>
</body>
</html>