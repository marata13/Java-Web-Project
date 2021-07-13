package com.web.servlets.users.admin;

import com.core.system.systemUsers.Admin;
import com.database.Database;
import com.database.QueryManager;
import com.database.queries.Queries;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static com.core.security.SecurityManager.getHash;

@WebServlet("/AddPatient")
public class AddPatient extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        try {
            QueryManager.saveToDatabase(
                    Queries.ADD_PATIENT.query,
                    Database.getConnection(),
                    "patient",
                    Long.parseLong(request.getParameter("patient_amka")),
                    request.getParameter("patient_username"),
                    getHash(request.getParameter("patient_password")),
                    request.getParameter("patient_name"),
                    request.getParameter("patient_surname")
            );

            out.println("<html><head><title>Add Patient</title></head>" +
                    "<body><div style=\"text-align: center;\"><h1>You just added Patient "+request.getParameter("patient_surname")+" to the database" +
                    "</h1><br><a href=\"/Kotza_Project_Web_war/index.jsp\">Click here to go back </a></div>" +
                    "</body></html>");

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
