package com.web.servlets.session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/DeleteAppointments")
public class DeleteAppointments extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            com.database.QueryManager.deleteAppointment(Integer.parseInt(request.getParameter("appointmentID")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out=response.getWriter();
        out.println("<h1>Your appointment has been deleted</h1>" +
                "press here to go back <a href=\"/Kotza_Project_Web_war/index.jsp\"><button type=\"button\">Click Me!</button></a>");
    }

}
