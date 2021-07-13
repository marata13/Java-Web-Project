package com.web.servlets.users.doctor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/specificPatient")
public class specificPatient extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            com.core.system.management.Appointment.showForSpecificPatient(request.getParameter("doctorAMKA"),
                    request.getParameter("patient_name"),request.getParameter("patient_surname"),out);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
