<%@ page import="java.sql.SQLException" %>
<%@ page import="com.core.system.management.Appointment" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Home</title>
    <link rel="stylesheet" href="../css/patient/patient.css">
    <script type="text/javascript" src="../js/userLogin.js"></script>
</head>
<body>

    <%
        if (request.getSession().getAttribute("username") == null) response.sendError(403);
        // Εδω λαμβανουμε ολα τα σχετικα δεδομενα απο το servlet.
        String username = (String) request.getSession().getAttribute("username");
        String name = (String) request.getSession().getAttribute("name");
        String surname = (String) request.getSession().getAttribute("surname");
        String patientAMKA = (String) request.getSession().getAttribute("patient_amka");
    %>

    <table class="patientInfo">
        <tr>
            <th>
                username
            </th>
            <th>
                name
            </th>
            <th>
                surname
            </th>
            <th>
                AMKA
            </th>
        </tr>
        <tr>
            <td>
                <%=username%>
            </td>
            <td>
                <%=name%>
            </td>
            <td>
                <%=surname%>
            </td>
            <td>
                <%=patientAMKA%>
            </td>
        </tr>
    </table>

    <table class="appointments">
        <%
            try {
                Appointment.showPreviousAppointments(username, out);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </table>

    <form action="../LogoutServlet" method="post" onsubmit="return validateLogout()">
        <input type="submit" value="Logout" class="logoutButton" style="position: absolute; right: 1%;">
    </form>

    <br><br>
    <table class="form">
    <div style="text-align: center;">
    <FORM action="/Kotza_Project_Web_war/availableAppointmentServlet" method="post">
        <label for="availableAppointments"><p style="color:white">Choose a doctor specialty to find available appointments:</p></label>
        <select name="doctorSpecialty" id="availableAppointments">
            <option value="pathologist">pathologist</option>
            <option value="ophthalmologist">ophthalmologist</option>
            <option value="orthopedic">orthopedic</option>
        </select>
        <br><br>
        <input type="hidden" name="username" value="<%=username%>">
        <input type="hidden" name="surname" value="<%=surname%>">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="submit" value="Submit">

    </FORM>
    </div>
    </table>
</body>
</html>
