package com.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        DataSource source;
        InitialContext ic;

        try {
            if (conn == null || conn.isClosed()) {
                ic = new InitialContext();
                source = (DataSource) ic.lookup("java:comp/env/jdbc/postgresql_resource");
                Database.conn = source.getConnection();
            }
        }
        catch (NamingException | SQLException e ) {
            e.printStackTrace();
        }

        return Database.conn;
    }
}
