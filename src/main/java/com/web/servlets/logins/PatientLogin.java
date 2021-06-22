package com.web.servlets.logins;

import com.core.exceptions.LoginFailure;
import com.core.system.systemUsers.Patient;
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
    
@WebServlet("/patientLogin")
public class PatientLogin extends HttpServlet {

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
        Patient patient = new Patient();
        HashMap<String, String> userDetails;

        try {
            patient.login(request.getParameter("username"), request.getParameter("password"), "patient");
            request.getSession().setAttribute("username", request.getParameter("username"));
            userDetails = patient.getUserDetails(request.getParameter("username"),"patient");
            // Εδω οριζουμε τι θα προωθησουμε στο jsp.
            request.setAttribute("username", userDetails.get("username"));
            request.setAttribute("name", userDetails.get("name"));
            request.setAttribute("surname", userDetails.get("surname"));
            request.setAttribute("patient_amka", userDetails.get("patient_amka"));
            // Προωθουμε τα δεδομενα στο jsp.
            this.getServletContext().getRequestDispatcher("/users/patientHome.jsp").forward(request, response);
        }
        catch(LoginFailure | IOException | SQLException | ServletException e){
            // do something.
            this.getServletContext().getRequestDispatcher("/errors/error.jsp").forward(request, response);
        }
    }
}
