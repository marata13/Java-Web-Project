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
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
    
@WebServlet("/PatientLogin")
public class PatientLogin extends HttpServlet {
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
            userDetails = patient.getUserDetails(request.getParameter("username"),"patient");
            // Εδω οριζουμε τι θα προωθησουμε στο jsp.
            request.getSession().setAttribute("username", userDetails.get("username"));
            request.getSession().setAttribute("name", userDetails.get("name"));
            request.getSession().setAttribute("surname", userDetails.get("surname"));
            request.getSession().setAttribute("patient_amka", userDetails.get("patient_amka"));
            response.sendRedirect(request.getContextPath()+"/users/patientHome.jsp");
        }
        catch(LoginFailure | IOException | SQLException | NoSuchAlgorithmException e){
            // do something.
            this.getServletContext().getRequestDispatcher("/errors/error.jsp").forward(request, response);
        }
    }
}
