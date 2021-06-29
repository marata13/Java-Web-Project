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
</body>
</html>
