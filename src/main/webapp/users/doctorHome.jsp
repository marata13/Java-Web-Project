<%@ page import="java.sql.SQLException" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="static com.database.QueryManager.startOfWeek" %>
<%@ page import="static com.database.QueryManager.endOfWeek" %>
<%@ page import="static com.database.QueryManager.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: rounnus
  Date: 5/29/21
  Time: 8:21 PM
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
<br>Schedule your weekly program:
<form action="../DoctorServlet" method="post">
    <br>Week: <%out.print(startOfWeek(28).getDayOfMonth());%> to <%out.print(endOfWeek(28));%>
    <button value="Yes">Yes, i want to schedule another week</button> &ensp; <button value="No">No, i'll leave it for later</button>
</form>

<%
    String Yes = (String) request.getAttribute("Yes");
    if (/*Yes.equals("Yes")*/true){
        out.print("<form action=\"../DoctorServlet\" method=\"post\">");
        out.println("<TABLE BORDER=1>");
        out.println("<TR>");
        for(int i=0;i<=6;i++) {
            out.print("<TH>" + startOfWeek(28).plusDays(i).getDayOfWeek().toString() + " " + startOfWeek(28).plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        for(int j=0;j<=10;j++) {
            out.println("<TR>");
            for(int i=1;i<=7;i++) {
                out.print("<TD>"+ "<button value=\"available"+  j + i + "\">Not Available</button>"); //με κλικ τα γράμματα θα αλλάζουν σε available
            }
            out.println();
        }
        out.print("</form>");
        out.println();
        out.println();
    }
%>
<br>Choose a day to see its schedule:
<!-- εδώ θα μπει ένα ημερολόγιο, όπου ο γιατρός θα μπορεί να επιλέγει μια από τις επόμενες 30 ημέρες -->
<%
    //String out1 = (String) request.getAttribute("out1");
    String date = (String) request.getAttribute("date");
    if (date != null) {
        try {
            com.core.system.management.Appointment.showAppointmentsPerDay(request.getParameter("doctorAMKA"), date.toString(), out);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>
<br><br>Choose a week to see its schedule:
<form action="../DoctorServlet" method="post">
<select name="program">
    <option value="null"></option>
    <option value=<% startOfWeek(0);%>><%out.print(startOfWeek(0).getDayOfMonth());%> to <%out.print(endOfWeek(0));%></option>
    <option value=<% startOfWeek(7);%>><%out.print(startOfWeek(7).getDayOfMonth());%> to <%out.print(endOfWeek(7));%></option>
    <option value=<% startOfWeek(14);%>><%out.print(startOfWeek(14).getDayOfMonth());%> to <%out.print(endOfWeek(14));%></option>
    <option value=<% startOfWeek(21);%>><%out.print(startOfWeek(21).getDayOfMonth());%> to <%out.print(endOfWeek(21));%></option>
    <option value=<% startOfWeek(28);%>><%out.print(startOfWeek(28).getDayOfMonth());%> to <%out.print(endOfWeek(28));%></option>
</select>
    <br><br>
    <input type="submit" value="Submit">
</form>
<%
    //String out2 = (String) request.getAttribute("out2");
    String program = (String) request.getAttribute("program");
    if(!program.equals("null"))
    com.core.system.management.Appointment.showAppointmentForSequenceOfDays(doctorAMKA, program, 7, out);
%>
<!-- ο γιατρός θα μπορεί να αναζητήσει αν έχει κανονίσει ραντεβού με έναν συγκεκριμένο ασθενή-->
<form action="../DoctorServlet" method="post">
    <br><br><br>Find a scheduled appointment with a specific patient!
    <br>Enter the name of the patient: <input type="text" name="patient_name">
    <br>Enter the surname of the patient: <input type="text" name="patient_surname">
    <br><button>Search</button>
</form>
<% try {
    String patient_name = (String) request.getAttribute("patient_name");
    String patient_surname = (String) request.getAttribute("patient_surname");
    if(patient_name != null && patient_surname != null) {
        com.core.system.management.Appointment.showAppointmentDoctorSide(doctorAMKA, patient_name, patient_surname, out);
    }
} catch (SQLException e) {
    e.printStackTrace();
} %>
</body>
</html>