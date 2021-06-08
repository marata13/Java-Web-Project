package com.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() {
        DataSource source = null;
        InitialContext ic = null;
        Connection conn = null;


        try {
            ic = new InitialContext();
            source = (DataSource) ic.lookup("java:comp/env/jdbc/postgresql_resource");
            conn = source.getConnection();
        } catch (NamingException | SQLException e ) {
            e.printStackTrace();
        }

        return conn;
    }
}
