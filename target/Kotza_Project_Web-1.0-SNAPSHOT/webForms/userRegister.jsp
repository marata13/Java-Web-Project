<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <script type="text/javascript" src="../js/register.js"></script>
</head>
<body>
<form name="registerForm" onsubmit="return validateRegister()" action="../AddPatient" method="post" >
    Enter your name: <input type="text" name="patient_name"><br>
    Enter your surname: <input type="text" name="patient_surname"><br>
    Enter your AMKA: <input type="number" name="patient_amka"><br>
    Enter your username: <input type="text" name="patient_username"><br>
    Enter your password: <input type="password" name="patient_password"><br>
    Confirm your password: <input type="password" name="confirm_password">
    <input type="submit">
</form>
</body>
</html>
