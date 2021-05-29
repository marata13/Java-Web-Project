package com.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/db")
public class DBConnect extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DataSource source = null;
        InitialContext ic = null;

        try {
            ic = new InitialContext();
            source = (DataSource) ic.lookup("java:comp/env/jdbc/postgresql_resource");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = source.getConnection();
            System.out.println("Connection established!");

            /*PreparedStatement statement = conn.prepareStatement(
                    "SELECT name FROM Patient WHERE Patient == ? "
            );

            statement.setString(1,"PATIENT");

            ResultSet result = statement.getResultSet();*/

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

}
