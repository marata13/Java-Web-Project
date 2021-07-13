<%@ page import="static com.database.QueryManager.startOfWeek" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
    <link rel="stylesheet" href="./css/index.css">
</head>
<body>
    <div id="title">
        <b>Login System</b>
    </div>

    <button class="linkButtonsRow1" onclick="window.location.href='webForms/userRegister.jsp'">
        Register
    </button>
    <button class="linkButtonsRow1" onclick="window.location.href='webForms/patientLogin.jsp'">
        Login as Patient
    </button>
    <br>
    <button class="linkButtonsRow2" onclick="window.location.href='webForms/doctorLogin.jsp'">
        Login as Doctor
    </button>
    <button class="linkButtonsRow2" onclick="window.location.href='webForms/adminLogin.jsp'">
        Login as Admin
    </button>
</body>
</html>