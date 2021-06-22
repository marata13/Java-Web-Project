<%--
  Created by IntelliJ IDEA.
  User: mirto
  Date: 6/22/2021
  Time: 1:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="../registrationServlet" method="post">
    Enter your name: <input type="text" name="name">
    Enter your surname: <input type="text" name="surname">
    Enter your AMKA: <input type="number" name="amka">
    Enter your username: <input type="text" name="username">
    Enter your password: <input type="password" name="password">
    Confirm your password: <input type="password" name="confirm_password">
    <input type="submit">
</form>
</body>
</html>
