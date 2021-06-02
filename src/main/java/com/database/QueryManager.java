package com.database;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("")
public class QueryManager {

    /**
     * Σε αυτη την μεθοδο ψαχνουμε στην βαση δεδομενων για τα στοιχεια συνδεσης
     * ενος συγκερκιμενου χρηστη και αφου τα βρουμε τα επιστρεφουμε μεσα σε μια
     * δομη τυπου HashMap.
     *
     * @param username Το username με το οποιο θα παρουμε τα δεδομενα του χρηστη απο την βαση.
     * @param conn Η συνδεση προς την βαση.
     * @param table O πινακας στον οποιο βρισκονται τα δεδομενα.
     * @return HashMap<String,String>
     */
    public static HashMap<String, String> getCredentials(String username, Connection conn, String table)  {

        HashMap<String, String> credentials = new HashMap<>();
        ResultSet resultsFromDB;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    MessageFormat.format("SELECT username, password FROM {0} WHERE username = ?", table)
            );
            // Θετονται οι καταλληλες τιμες στις παραμετρους του ερωτηματος.
            // Δεν γινεται SQL Injection.
            preparedStatement.setString(1, username.trim());
            preparedStatement.executeQuery();

            resultsFromDB = preparedStatement.getResultSet();
            resultsFromDB.next();
            // Καταχωρηση των δεδομενων σε ενα HashMap.
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

    public static HashMap<String, String> getUserDetails(String username, Connection conn, String table){
        HashMap<String, String> details = new HashMap<>();
        ResultSet resultsFromDB;

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    MessageFormat.format("SELECT * FROM {0} WHERE username = ?", table)
            );
            // Θετονται οι καταλληλες τιμες στις παραμετρους του ερωτηματος.
            // Δεν γινεται SQL Injection.
            preparedStatement.setString(1, username.trim());
            preparedStatement.executeQuery();

            resultsFromDB = preparedStatement.getResultSet();
            resultsFromDB.next();
            // Καταχωρηση των δεδομενων σε ενα HashMap.
            details.put("patientAMKA", resultsFromDB.getString("patientAMKA"));
            details.put("username", resultsFromDB.getString("username"));
            resultsFromDB.next();
            details.put("name", resultsFromDB.getString("name"));
            details.put("surname", resultsFromDB.getString("surname"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }
}