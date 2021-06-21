<%@ page import="static com.database.QueryManager.startOfWeek" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Welcome to our site!
    <h3>Here you can schedule medical appointments</h3>
</h1>
<br/>
<a href="webForms/patientLogin.jsp">Login as Patient</a>
<a href="webForms/doctorLogin.jsp">Login as Doctor</a>
<a href="webForms/adminLogin.jsp">Login as Admin</a>
<br>
<%//  out.print(startOfWeek()); %>
</body>
</html>