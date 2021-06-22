package com.web.servlets.logins;

import com.core.exceptions.LoginFailure;
import com.core.system.systemUsers.Doctor;
import com.database.Database;
import com.database.QueryManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/doctorLogin")
public class DoctorLogin extends HttpServlet {

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
        Doctor doctor = new Doctor();
        HashMap<String, String> userDetails;

        try {
            doctor.login(request.getParameter("username"), request.getParameter("password"), "doctor");
            request.getSession().setAttribute("username", request.getParameter("username"));
            userDetails = doctor.getUserDetails(request.getParameter("username"),"doctor");
            // Εδω οριζουμε τι θα προωθησουμε στο jsp.
            request.setAttribute("username", userDetails.get("username"));
            request.setAttribute("name", userDetails.get("name"));
            request.setAttribute("surname", userDetails.get("surname"));
            request.setAttribute("specialty", userDetails.get("specialty"));
            request.setAttribute("doctor_amka", userDetails.get("doctor_amka"));
            // Προωθουμε τα δεδομενα στο jsp.
            this.getServletContext().getRequestDispatcher("/users/doctorHome.jsp").forward(request, response);
        }
        catch(LoginFailure | IOException | SQLException | ServletException e){
            // do something.
            this.getServletContext().getRequestDispatcher("/errors/error.jsp").forward(request, response);
        }
    }
}
