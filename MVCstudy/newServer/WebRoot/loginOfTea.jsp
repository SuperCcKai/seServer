<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>教师登录</title>
</head>
<body>
	<form action="http://localhost:8080/newServer/RegisterOfTea" method="post">
		姓名：<input type="text" name="username" id="username" />
		<br/>
		密码：<input type="password" name="password" id="password" />
		<input type="submit" />
	</form>
</body>
</html>