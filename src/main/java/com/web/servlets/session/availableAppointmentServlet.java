package com.web.servlets.session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/availableAppointmentServlet")
public class availableAppointmentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        out.print("<HTML><HEAD><TITLE> Available apppointments</TITLE></HEAD>" +
                "<BODY><CENTER>");

        try {
            com.core.system.management.Appointment.makeAppointmentBySpeciality(out,request.getParameter("username"),
                    request.getParameter("surname"),request.getParameter("name")
                    ,request.getParameter("doctorSpecialty"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.print("</CENTER></BODY></HTML>");
    }
}
