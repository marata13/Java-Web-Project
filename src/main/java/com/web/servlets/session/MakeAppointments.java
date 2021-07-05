package com.web.servlets.session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Locale;

@WebServlet("/MakeAppointments")
public class MakeAppointments extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out=response.getWriter();
        try {
            if(com.database.QueryManager.makeAppointment(request.getParameter("username").toLowerCase(),
                    request.getParameter("surname").toLowerCase(),
                    request.getParameter("name").toLowerCase(),Integer.parseInt(request.getParameter("appointmentID")))==0)
            {
                out.println("<h1>There has been a problem your appointment has not been scheduled</h1>" +
                        "press here to go back <a href=\"/Kotza_Project_Web_war/users/patientHome.jsp\"><button type=\"button\">Click Me!</button></a>");
            }else{
                com.database.QueryManager.makeAppointment(request.getParameter("username").toLowerCase(),
                        request.getParameter("surname").toLowerCase(),
                        request.getParameter("name").toLowerCase(),Integer.parseInt(request.getParameter("appointmentID")));
                out.println("<h1>Your appointment has been scheduled</h1>" +
                        "press here to go back <a href=\"/Kotza_Project_Web_war/users/patientHome.jsp\"><button type=\"button\">Click Me!</button></a>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
