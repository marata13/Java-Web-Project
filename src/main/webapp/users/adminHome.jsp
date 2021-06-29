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
<body>
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
<% /*try {
    com.core.system.management.Appointment.showPreviousAppointments(username, out);
} catch (SQLException e) {
    e.printStackTrace();
}*/ %>
</body>
</html>
