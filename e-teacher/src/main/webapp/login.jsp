<%--
  Created by IntelliJ IDEA.
  User: john
  Date: 2017/7/28
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/account/doAjax.action" method="post" >
账号：<input type="text" name="account"><br>
密码：<input type="password" name="pwd">
<input type="submit" value="提交">
</form>
</body>
</html>
