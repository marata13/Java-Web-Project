<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: rounnus
  Date: 5/29/21
  Time: 8:21 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home</title>
</head>
<body><div style="text-align: center;">
    <%
        if (request.getSession().getAttribute("username") == null) response.sendError(403);
        // Εδω λαμβανουμε ολα τα σχετικα δεδομενα απο το servlet.
        String username = (String) request.getSession().getAttribute("username");
        String AdminID = (String) request.getSession().getAttribute("admin_userid");
    %>

THIS IS HOME
    <table>

        <tr>
            <td>username: <%=username%></td>
        </tr>
        <tr>
            <td>ID: <%=AdminID%></td>
        </tr>

    </table>

<br><br>

        <h1>Enter a new doctor:</h1><br>
        <FORM action="../AddDoctor" method="post">
            Enter the amka of the doctor:<input type="text" name="doctor_amka">
            <br>Enter the username of the doctor: <input type="text" name="doctor_username">
            <br>Enter the password of the doctor: <input type="password" name="doctor_password">
            <br>Enter the specialty of the doctor: <input type="text" name="doctor_specialty">
            <br>Enter the surname of the doctor: <input type="text" name="doctor_surname">
            <br>Enter the name of the doctor: <input type="text" name="doctor_name">
            <input type="hidden" name="admin_id" value="<%=AdminID%>">
            <input type="submit" value="Submit">

        </FORM><br><br>
        <h2>Delete a doctor:</h2><br>
        <form action="../DeleteDoctor" method="post">
            Enter the amka of the doctor you want to delete : <input type="text" name="doctor_amka">
            <br><input type="submit" value="Submit">
        </form>
    <br><br>

    <h3>Enter a new patient:</h3><br>
    <form action="../AddPatient" method="post">
        Enter the amka of the patient:<input type="text" name="patient_amka">
        <br>Enter the username of the patient: <input type="text" name="patient_username">
        <br>Enter the password of the patient: <input type="password" name="patient_password">
        <br>Enter the name of the patient: <input type="text" name="patient_name">
        <br>Enter the surname of the patient: <input type="text" name="patient_surname">
        <input type="submit" value="Submit">
    </form>
    </div>

<br><form action="../LogoutServlet" method="post" onsubmit="return validateLogout()">
    <input type="submit" value="Logout" class="logoutButton" style="position: absolute; right: 1%;">
</form>
</body>
</html>
