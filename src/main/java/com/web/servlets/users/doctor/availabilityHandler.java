package com.web.servlets.users.doctor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/availabilityHandler")
public class availabilityHandler extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        ArrayList<String> parameterNames = new ArrayList<String>();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            parameterNames.add(parameterName);
        }
        for(int i=0;i<parameterNames.size();i++){
            try {
                com.database.QueryManager.availabilityDeclaration(request.getParameter(parameterNames.get(i)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.println("<html><body><h1>You have declared your appointments" +
                "</h1><br><a href=\"/Kotza_Project_Web_war/users/doctorHome.jsp\">Click here to go back </a>" +
                "</body></html>");
    }

}
