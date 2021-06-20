<%@ page import="java.sql.SQLException" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="static com.database.QueryManager.startOfWeek" %>
<%@ page import="static com.database.QueryManager.endOfWeek" %>
<%@ page import="static com.database.QueryManager.*" %><%--
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
    String doctorAMKA = (String) request.getAttribute("doctorAMKA");
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
Ημερήσιο Πρόγραμμα:
<!-- εδώ θα μπει ένα ημερολόγιο, όπου ο γιατρός θα μπορεί να επιλέγει μια από τις επόμενες 30 ημέρες -->
<% try {
    LocalDate date = java.time.LocalDate.of(2020, 7, 20);
    com.core.system.management.Appointment.showAllAppointments(doctorAMKA, date, out);
} catch (SQLException e) {
    e.printStackTrace();
} %>
Εβδομαδιαίο Πρόγραμμα:
<form action="/doctorHome">
<select name="program">
    <option value=<% startOfWeek(0);%>><%out.print(startOfWeek(0));%> εώς <%out.print(dateStr(0));%></option>
    <option value=<% startOfWeek(7);%>><%out.print(startOfWeek(7).getDayOfMonth());%> εώς <%out.print(dateStr(7));%></option>
    <option value=<% startOfWeek(14);%>><%out.print(startOfWeek(14).getDayOfMonth());%> εώς <%out.print(dateStr(14));%></option>
    <option value=<% startOfWeek(21);%>><%out.print(startOfWeek(21).getDayOfMonth());%> εώς <%out.print(dateStr(21));%></option>
    <option value=<% startOfWeek(28);%>><%out.print(startOfWeek(28).getDayOfMonth());%> εώς <%out.print(dateStr(28));%></option>
</select>
    <br><br>
    <input type="submit" value="Submit">
</form>

<% /*try {
    com.core.system.management.Appointment.showAllAppointments(doctorAMKA, request.getParameter("program"), out);
} catch (SQLException e) {
    e.printStackTrace();
}*/ %>
<!-- ο γιατρός θα μπορεί να αναζητήσει αν έχει κανονίσει ραντεβού με έναν συγκεκριμένο ασθενή-->
Find a scheduled appointment with a specific patient!
Enter the name of the patient: <input type="text" name="name">
Enter the surname of the patient: <input type="text" name="surname">
<button>Search</button>
<% /*try {
    String patient_name = "Petros";
    String patient_surname = "Papadopoulos";
    com.core.system.management.Appointment.showAppointmentDoctorSide(doctorAMKA, patient_name, patient_surname);
} catch (SQLException e) {
    e.printStackTrace();
}*/ %>
</body>
</html>