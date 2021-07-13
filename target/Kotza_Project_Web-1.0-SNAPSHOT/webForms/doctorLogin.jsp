<%--
  Created by IntelliJ IDEA.
  User: mirto
  Date: 6/19/2021
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Doctor Login</title>
    <link rel="stylesheet" href="../css/userLogin.css">
    <script type="text/javascript" src="../js/userLogin.js"></script>
</head>
<body>
    <div style="text-align: center;">
        <form name="patientLoginForm" action="../DoctorLogin" method="post" onsubmit="return validateInput()">
            <div id = "username">
                <b>Username</b>
            </div><br>
            <input type="text" name="username"><br>
            <div id = "password">
                <b>Password</b>
            </div><br>
            <input type="password" name="password"><br><br>
            <input type="submit" id = "loginButton" value="Login">
        </form>
    </div>

</body>
</html>
