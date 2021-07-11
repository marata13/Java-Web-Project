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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


import static com.core.security.SecurityManager.getHash;


@WebServlet("/AddDoctor")
public class AddDoctor extends HttpServlet {
    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            QueryManager.saveToDatabase(
                    Queries.ADD_DOCTOR.query,
                    Database.getConnection(),
                    "doctor",
                    Long.parseLong(request.getParameter("doctor_amka")),
                    Long.parseLong(request.getParameter("admin_id")),
                    request.getParameter("doctor_username"),
                    getHash(request.getParameter("doctor_password")),
                    request.getParameter("doctor_specialty"),
                    request.getParameter("doctor_surname"),
                    request.getParameter("doctor_name")
            );

            out.println("<html><head><title>Add Doctor</title></head>" +
                    "<body><div style=\"text-align: center;\"><h1>You just added doctor "+request.getParameter("doctor_surname")+" to the database" +
                    "</h1><br><a href=\"/Kotza_Project_Web_war/users/adminHome.jsp\">Click here to go back </a></div>" +
                    "</body></html>");

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
       /* try {
            if(0==saveToDatabase(Queries.ADD_DOCTOR.query,
                        com.database.Database.getConnection(),"doctor"
                    ,Long.parseLong(request.getParameter("doctor_amka")),
                    Long.parseLong(request.getParameter("admin_id"))
                    ,request.getParameter("doctor_username"),
                    getHash(request.getParameter("doctor_password"))
                    ,request.getParameter("doctor_specialty"),
                    request.getParameter("doctor_surname")
                    ,request.getParameter("doctor_name")))
            {
                        out.println("<html><body>Error</body></html>");}
            else{
                saveToDatabase(Queries.ADD_DOCTOR.query,
                        com.database.Database.getConnection(),"doctor",Long.parseLong(request.getParameter("doctor_amka")),
                        Long.parseLong(request.getParameter("admin_id")),request.getParameter("doctor_username"),
                        getHash(request.getParameter("doctor_password")),request.getParameter("doctor_specialty"),
                        request.getParameter("doctor_surname"),request.getParameter("doctor_name"));
                out.println("<html><head><title>Add Doctor</title></head>" +
                        "<body><div style=\"text-align: center;\"><h1>You just added doctor "+request.getParameter("doctor_surname")+" to the database" +
                        "</h1><br><a href=\"/Kotza_Project_Web_war/users/adminHome.Jsp\">Click here to go back </a></div>" +
                        "</body></html>");
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/

    }
}
