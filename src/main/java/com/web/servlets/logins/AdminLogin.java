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
import java.security.NoSuchAlgorithmException;
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
            userDetails = admin.getUserDetails(request.getParameter("username"),"admin");
            // Εδω οριζουμε τι θα προωθησουμε στο jsp.
            request.getSession().setAttribute("username", userDetails.get("username"));
            request.getSession().setAttribute("name", userDetails.get("name"));
            request.getSession().setAttribute("admin_userid", userDetails.get("admin_userid"));
            response.sendRedirect(request.getContextPath()+"/users/adminHome.jsp");
        }
        catch(LoginFailure | IOException | SQLException | NoSuchAlgorithmException e){
            // do something.
            this.getServletContext().getRequestDispatcher("/users/error.jsp").forward(request, response);
        }
    }
}
