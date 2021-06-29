<%@ page import="java.sql.SQLException" %>
<%@ page import="com.core.system.systemUsers.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Home</title>
    <link rel="stylesheet" href="../css/patientHome.css">
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

    <div class="patientInfos">

    </div>


    <form action="../LogoutServlet" method="post">
        <input type="submit" style="position: absolute; right: 1%;">
    </form>
</body>
</html>
