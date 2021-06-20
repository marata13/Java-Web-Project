package com.web.servlets.users;

import com.core.exceptions.LoginFailure;
import com.core.system.systemUsers.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

@WebServlet("/doctorHome")
public class DoctorServlet extends HttpServlet {
    /*@Override
    public void init() {
    }*/

    /**
     *
     * @Source https://stackoverflow.com/questions/21327772/get-value-of-select-in-servlet/21327848
     * @param request
     * @param response
     */

    /*@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        request.getParameter("program");

        try {
            // Εδω οριζουμε τι θα προωθησουμε στο jsp.
            // Προωθουμε τα δεδομενα στο jsp.
            this.getServletContext().getRequestDispatcher("/users/doctorHome.jsp").forward(request, response);
        }
        catch(LoginFailure | IOException | SQLException | ServletException e){
            System.out.println("ok");
        }
    }*/
}
