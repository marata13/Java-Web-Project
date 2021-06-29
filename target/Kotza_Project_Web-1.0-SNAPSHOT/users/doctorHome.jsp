<%@ page import="static com.database.QueryManager.startOfWeek" %>
<%@ page import="static com.database.QueryManager.endOfWeek" %><%--
  Created by IntelliJ IDEA.
  User: mirto
  Date: 6/25/2021
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Doctor Home</title>
</head>
<body>
    <%
    // Εδω λαμβανουμε ολα τα σχετικα δεδομενα απο το servlet.
    String username = (String) request.getAttribute("username");
    String name = (String) request.getAttribute("name");
    String surname = (String) request.getAttribute("surname");
    String specialty = (String) request.getAttribute("specialty");
    String doctorAMKA = (String) request.getAttribute("doctor_amka");
%>

THIS IS HOME
<table>

    <tr>
        <td>username: <%=username%></td>
    </tr>
    <tr>
        <td>name: <%=name%></td>
    </tr>
    <tr>
        <td>surname: <%=surname%></td>
    </tr>
    <tr>
        <td>specialty: <%=specialty%></td>
    </tr>
    <tr>
        <td>doctorAMKA: <%=doctorAMKA%></td>
    </tr>
</table>


    <br><br>Choose a week to see its schedule:
    <form action="weeklyProgram" method="get">
        <select name="program">
            <option value="1"   selected="selected"><%out.print(startOfWeek(0).getDayOfMonth());%> to <%out.print(endOfWeek(0));%></option>
            <option value="2"><%out.print(startOfWeek(7).getDayOfMonth());%> to <%out.print(endOfWeek(7));%></option>
            <option value="3"><%out.print(startOfWeek(14).getDayOfMonth());%> to <%out.print(endOfWeek(14));%></option>
            <option value="4"><%out.print(startOfWeek(21).getDayOfMonth());%> to <%out.print(endOfWeek(21));%></option>
            <option value="5"><%out.print(startOfWeek(28).getDayOfMonth());%> to <%out.print(endOfWeek(28));%></option>
        </select>
        <br><br>
        <input type="hidden" name="doctorAMKA" value="<%=doctorAMKA%>">
        <input type="submit" value="Submit">
    </form>
<br><br>
    <form action="../DoctorServlet" method="post">
    <br><br><br>Find a scheduled appointment with a specific patient!
    <br>Enter the name of the patient: <input type="text" name="patient_name">
    <br>Enter the surname of the patient: <input type="text" name="patient_surname">
    <br><input type="submit" value="Submit">
    </form>



</body>
</html>