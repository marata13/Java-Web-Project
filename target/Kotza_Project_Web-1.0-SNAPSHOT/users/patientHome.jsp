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
    <title>Patient Home</title>
</head>
<body>
    <%
        // Εδω λαμβανουμε ολα τα σχετικα δεδομενα απο το servlet.
        String username = (String) request.getAttribute("username");
        String name = (String) request.getAttribute("name");
        String surname = (String) request.getAttribute("surname");
        String patientAMKA = (String) request.getAttribute("patient_amka");
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
            <td>patientAMKA: <%=patientAMKA%></td>
        </tr>
    </table>
    <% try {
        com.core.system.management.Appointment.showPreviousAppointments(username, out);
    } catch (SQLException e) {
        e.printStackTrace();
    } %>
</body>
</html>
