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
<form action="${pageContext.request.contextPath}/Class/addClass.action" method="post" >
账号：<input type="text" name="inviteCode.code"><br>
密码：<input type="text" name="user.userId">
     <input type="text" name="className">
<input type="submit" value="提交">
</form>
</body>
</html>
