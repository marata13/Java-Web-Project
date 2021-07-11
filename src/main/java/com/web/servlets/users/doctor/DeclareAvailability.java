package com.web.servlets.users.doctor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Random;

@WebServlet("/declareAvailability")
public class DeclareAvailability extends HttpServlet {

    public static int getRandomAppointmentId() {
        Random random = new Random();
        return random.nextInt(1000000000);
    }

    static String queryMaker(LocalDate date , int time,String amka, String name,String surname, String specialty){
        return "insert into appointment(appointment_id,date,time,doctor_amka,doctor_name,doctor_surname,doctor_specialty,patient_username,patient_surname,patient_name)" +
                " values("+getRandomAppointmentId()+",'"+date+"','"+time+":00',"+amka+",'"+name+"','"+surname+"','"+specialty+"',null,null,null);";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Availability Declaration</title></head><br>" +
                "<body>");


        LocalDate date = LocalDate.parse(request.getParameter("date"));


        out.println("<form action=\"/Kotza_Project_Web_war/availabilityHandler\" method=\"post\">");
        String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
        for(int j=0;j<5;j++) {
            out.println("<table border=1><tr><th>"+days[j]);
            for (int i = 9 , k=1; i <= 15; i++,k++) {
                out.print("<tr><td>" + i);
                out.println("<td><input type=\"checkbox\" id=\""+(k+(j*7))+"\" name=\""+(k+(j*7))+"\" value=\""+queryMaker(date.plusDays(j)
                ,i,request.getParameter("amka"),request.getParameter("name"), request.getParameter("surname"),request.getParameter("specialty"))+"\">");
            }
            out.println("</table>");
        }
        out.println("<tr><td><input type=\"submit\" value=\"declare\" name =\"submit\"></form>");
    }
}
