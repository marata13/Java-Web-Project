package com.web.servlets;

import com.core.exceptions.LoginFailure;
import com.core.system.systemUsers.Patient;
import com.database.Database;
import com.database.QueryManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/patientLoginServlet")
public class PatientLogin extends HttpServlet {
    // toDO Make that thing to work for every login page and smaller.

    private Connection conn;

    @Override
    public void init() {
        conn = Database.getConnection();
    }

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Patient p1 = new Patient();
        try{
            p1.login(request.getParameter("username"), request.getParameter("password"), "patient");
            response.sendRedirect(request.getContextPath()+"/users/patientHome.jsp");
        }
        catch(LoginFailure | IOException e){
            //toDO: write this on html
            System.out.println("Your login has failed! Please, try again...");
        }
    }

    /**
     *
     */
    @Override
    public void destroy() {
        try {
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
