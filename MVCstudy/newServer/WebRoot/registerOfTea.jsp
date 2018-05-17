<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>教师注册</title>
</head>
<body>
	<form action="http://localhost:8080/newServer/RegisterOfTea" method="post">
		工号：<input type="text" name="teaID" id="teaID" />
		<br/>
		姓名：<input type="text" name="username" id="username" />
		<br/>
		密码：<input type="password" name="password" id="password" />
		<br/>
		重复密码：<input type="password" id="Password_Again" />
		<br/>
		<input type="submit" />
	</form>
</body>
</html>