package com.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueryManager {

    /**
     *
     * @param username
     * @param conn
     * @param table
     * @return
     */
    public static HashMap<String, String> getCredentials(String username, Connection conn, String table)  {

        HashMap<String, String> credentials = new HashMap<>();
        ResultSet resultsFromDB = null;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT username, password FROM "+table+" WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            preparedStatement.executeQuery();

            resultsFromDB = preparedStatement.getResultSet();
            resultsFromDB.next();
            credentials.put("username", resultsFromDB.getString("username"));
            credentials.put("password", resultsFromDB.getString("password"));

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return credentials;
    }

    /**
     *
     * @return
     */
    public static ArrayList<String> getAppointments() {
        return null;
    }
}