package com.web.servlets;

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

        HashMap<String, String> dbCredentials;
        dbCredentials = QueryManager.getCredentials(request.getParameter("username"), conn, "patient");

        String fromUserUsername = request.getParameter("username");
        String fromUserPassword = request.getParameter("password");
        String fromDBUsername = dbCredentials.get("username");
        String fromDBPassword = dbCredentials.get("password");

        if (fromUserUsername.equals(fromDBUsername) && fromUserPassword.equals(fromDBPassword)) {
            try {
                response.sendRedirect(request.getContextPath()+"/users/patientHome.jsp");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Failed to login...");
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
