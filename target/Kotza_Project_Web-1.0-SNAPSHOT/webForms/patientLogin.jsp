<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Login</title>
</head>
<body>
    <form action="../patientServlet" method="post">
        Enter your username: <input type="text" name="username">
        Enter your password: <input type="password" name="password">
        <input type="submit">
    </form>

</body>
</html>