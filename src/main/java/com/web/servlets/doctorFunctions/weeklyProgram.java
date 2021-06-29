package com.web.servlets.doctorFunctions;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.core.system.management.Appointment.showAppointmentPerWeek;
import static com.core.system.management.Appointment.showAppointmentsPerDay;
import static com.database.QueryManager.startOfWeek;

@WebServlet("/weeklyProgram")
public class weeklyProgram  extends HttpServlet {
    @Override
    public void init() {
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            PrintWriter out = response.getWriter();
            String weeklyProgram = request.getParameter("program");
            String doctorAMKA = request.getParameter("doctorAMKA");
            int week = Integer.parseInt(weeklyProgram);
            switch(week) {
                case 1:
                    showAppointmentPerWeek(Long.parseLong(doctorAMKA),startOfWeek(0),out);
                    break;
                case 2:
                    showAppointmentPerWeek(Long.parseLong(doctorAMKA),startOfWeek(7),out);
                    break;
                case 3:
                    showAppointmentPerWeek(Long.parseLong(doctorAMKA),startOfWeek(14),out);
                    break;
                case 4:
                    showAppointmentPerWeek(Long.parseLong(doctorAMKA),startOfWeek(21),out);
                    break;
                case 5:
                    showAppointmentPerWeek(Long.parseLong(doctorAMKA),startOfWeek(28),out);
                    break;
            }

        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }


    }
}
