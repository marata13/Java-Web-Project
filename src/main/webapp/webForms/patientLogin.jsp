<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Login</title>
    <link rel="stylesheet" href="../css/patient/patient.css">
    <script type="text/javascript" src="../js/patient.js"></script>
</head>
<body>

    <div style="text-align: center;">
        <form name="patientLoginForm" action="../PatientLogin" method="post" onsubmit="return validateInput()">
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