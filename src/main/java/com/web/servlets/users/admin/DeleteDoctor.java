package com.web.servlets.users.admin;

import com.database.Database;
import com.database.QueryManager;
import com.database.queries.Queries;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/DeleteDoctor")
public class DeleteDoctor extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            QueryManager.saveToDatabase(
                    Queries.DELETE_DOCTOR.query,
                    Database.getConnection(),
                    "doctor",
                    Long.parseLong(request.getParameter("doctor_amka")));

            out.println("<html><head><title>Delete Doctor</title></head>" +
                    "<body><div style=\"text-align: center;\"><h1>You just deleted the doctor from the database" +
                    "</h1><br><a href=\"/Kotza_Project_Web_war/users/adminHome.jsp\">Click here to go back </a></div>" +
                    "</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
