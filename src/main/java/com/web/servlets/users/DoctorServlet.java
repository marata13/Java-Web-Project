package com.web.servlets.users;

import com.core.exceptions.LoginFailure;
import com.core.system.systemUsers.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@WebServlet("/doctorHome")
public class DoctorServlet extends HttpServlet {
    @Override
    public void init() {
    }

    /**
     *
     * @param request
     * @param response
     */

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //schedule weekly program
            String Yes = request.getParameter("Yes");
            request.setAttribute("Yes", Yes);
            //show program for specific day
            //PrintWriter out1 = response.getWriter();
            LocalDate date = java.time.LocalDate.of(2021, 7, 13);
            //com.core.system.management.Appointment.showAppointmentsPerDay(request.getParameter("doctorAMKA"), date.toString(), out1);
            //show program for specific week
            //PrintWriter out2 = response.getWriter();
            String program = request.getParameter("program");
            /*if(!request.getParameter("program").equals("null")) {
                com.core.system.management.Appointment.showAppointmentForSequenceOfDays(request.getParameter("doctorAMKA"), request.getParameter("program"), 7, out2);
            }*/
            //show appointments for specific patient
            //PrintWriter out3 = response.getWriter();
            String patient_name = request.getParameter("patient_name");
            String patient_surname = request.getParameter("patient_surname");
            /*if(patient_name != null && patient_surname != null) {
                com.core.system.management.Appointment.showAppointmentDoctorSide(request.getParameter("doctorAMKA"), patient_name, patient_surname, out3);
            }*/
            // Εδω οριζουμε τι θα προωθησουμε στο jsp.
            request.setAttribute("date", date);
            request.setAttribute("program", program);
            request.setAttribute("patient_name", patient_name);
            request.setAttribute("patient_surname", patient_surname);
            /*request.setAttribute("out1", out1);
            request.setAttribute("out2", out2);
            request.setAttribute("out3", out3);*/
            // Προωθουμε τα δεδομενα στο jsp.
            this.getServletContext().getRequestDispatcher("/users/doctorHome.jsp").forward(request, response);
        }catch(IOException /*| SQLException*/ | ServletException e){
            System.out.println("Something went wrong in DoctorServlet");
            this.getServletContext().getRequestDispatcher("/errors/error.jsp").forward(request, response);
        }
    }
}
