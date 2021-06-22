package com.web.servlets.logins;

import com.core.exceptions.LoginFailure;
import com.core.system.systemUsers.Admin;
import com.core.system.systemUsers.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet {

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
        Admin admin = new Admin();
        HashMap<String, String> userDetails;

        try {
            admin.login(request.getParameter("username"), request.getParameter("password"), "admin");
            request.getSession().setAttribute("username", request.getParameter("username"));
            userDetails = admin.getUserDetails(request.getParameter("username"),"admin");
            // Εδω οριζουμε τι θα προωθησουμε στο jsp.
            request.setAttribute("username", userDetails.get("username"));
            request.setAttribute("name", userDetails.get("name"));
            request.setAttribute("admin_userid", userDetails.get("admin_userid"));
            // Προωθουμε τα δεδομενα στο jsp.
            this.getServletContext().getRequestDispatcher("/users/adminHome.jsp").forward(request, response);
        }
        catch(LoginFailure | IOException | SQLException | ServletException e){
            // do something.
            this.getServletContext().getRequestDispatcher("/users/error.jsp").forward(request, response);
        }
    }
}
