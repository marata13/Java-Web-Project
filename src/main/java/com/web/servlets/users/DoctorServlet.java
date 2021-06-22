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
    /*@Override
    public void init() {
    }*/

    /**
     *
     * @param request
     * @param response
     */

    /*@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder MondayOfRequestedWeek = new StringBuilder();
            MondayOfRequestedWeek.append(request.getParameter("program"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //Monday
            LocalDate date1 = LocalDate.parse(MondayOfRequestedWeek, formatter);
            PrintWriter out = null;
            //request.getParameter()
            com.core.system.management.Appointment.showAllAppointments(request.getParameter("doctorAMKA"), date1, (JspWriter) out);
            //Tuesday
            LocalDate date2 = date1.plusDays(1);
            com.core.system.management.Appointment.showAllAppointments(request.getParameter("doctorAMKA"), date2, out);
            //Wednesday
            LocalDate date3 = date1.plusDays(2);
            com.core.system.management.Appointment.showAllAppointments(request.getParameter("doctorAMKA"), date3, out);
            //Thursday
            LocalDate date4 = date1.plusDays(3);
            com.core.system.management.Appointment.showAllAppointments(request.getParameter("doctorAMKA"), date4, out);
            //Friday
            LocalDate date5 = date1.plusDays(4);
            com.core.system.management.Appointment.showAllAppointments(request.getParameter("doctorAMKA"), date5, out);
            //Saturday
            LocalDate date6 = date1.plusDays(5);
            com.core.system.management.Appointment.showAllAppointments(request.getParameter("doctorAMKA"), date6, out);
            //Sunday
            LocalDate date7 = date1.plusDays(6);
            com.core.system.management.Appointment.showAllAppointments(request.getParameter("doctorAMKA"), date7, out);
            // Εδω οριζουμε τι θα προωθησουμε στο jsp.
            /*request.setAttribute("date1", date1);
            request.setAttribute("date2", date2);
            request.setAttribute("date3", date3);
            request.setAttribute("date4", date4);
            request.setAttribute("date5", date5);
            request.setAttribute("date6", date6);*/

            // Προωθουμε τα δεδομενα στο jsp.
            /*this.getServletContext().getRequestDispatcher("/users/doctorHome.jsp").forward(request, response);
        }catch(IOException | SQLException | ServletException e){
            System.out.println("ok");
        }
    }*/
}
