<%@ page import="static com.database.QueryManager.startOfWeek" %>
<%@ page import="static com.database.QueryManager.endOfWeek" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="static com.core.system.management.Appointment.showAppointmentPerWeek" %>
<%@ page import="static com.database.QueryManager.*" %>
<%@ page import="com.database.queries.Queries" %>
<%@ page import="com.database.Database" %><%--
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
        if (request.getSession().getAttribute("username") == null) response.sendError(403);
        // Εδω λαμβανουμε ολα τα σχετικα δεδομενα απο το servlet.
        String username = (String) request.getSession().getAttribute("username");
        String name = (String) request.getSession().getAttribute("name");
        String surname = (String) request.getSession().getAttribute("surname");
        String specialty = (String) request.getSession().getAttribute("specialty");
        String doctorAMKA = (String) request.getSession().getAttribute("doctor_amka");
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

    <br><br>
    <% try {
        for(int i=0;i<=4;i++) {

            if (!getDoctorAppointmentsPerWeek(startOfWeek(7 * i), Long.parseLong(doctorAMKA), Database.getConnection(), Queries.DOCTOR_APPOINTMENT_PER_WEEK.query).isBeforeFirst()) {
                out.println("You have to declare your availability for the week :"+startOfWeek(7*i).getDayOfMonth()+"-"
                +endOfWeek(7*i)+"<form action=\"/Kotza_Project_Web_war/declareAvailability\" method =\"post\">" +
                        "<input type=\"hidden\" name=\"amka\" value=\""+doctorAMKA+"\">" +
                        "<input type=\"hidden\" name=\"name\" value=\""+name+"\">" +
                        "<input type=\"hidden\" name=\"surname\" value=\""+surname+"\">" +
                        "<input type=\"hidden\" name=\"specialty\" value=\""+specialty+"\">" +
                        "<input type=\"hidden\" name=\"date\" value=\""+startOfWeek(7*i)+"\">" +
                        "<input type=\"submit\" value=\"go\" name =\"submit\">" +
                        "</form><br>");
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } %>


    <br><br>Choose a week to see its schedule:
    <form action="/Kotza_Project_Web_war/weeklyProgram" method="get">
        <select name="program">
            <option value="0"   selected="selected"><%out.print(startOfWeek(0).getDayOfMonth());%> to <%out.print(endOfWeek(0));%></option>
            <option value="1"><%out.print(startOfWeek(7).getDayOfMonth());%> to <%out.print(endOfWeek(7));%></option>
            <option value="2"><%out.print(startOfWeek(14).getDayOfMonth());%> to <%out.print(endOfWeek(14));%></option>
            <option value="3"><%out.print(startOfWeek(21).getDayOfMonth());%> to <%out.print(endOfWeek(21));%></option>
            <option value="4"><%out.print(startOfWeek(28).getDayOfMonth());%> to <%out.print(endOfWeek(28));%></option>
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