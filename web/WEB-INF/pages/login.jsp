<%--
  Created by IntelliJ IDEA.
  User: pdd20
  Date: 2020/8/2
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/login" method="post">
	用户名：
	<input type="text" name="username">
	密码:
	<input type="password" name="pwd">
	<input type="submit" value="登录">
</form>
</body>
</html>
