package com.database;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;

public class QueryManager {

    private static ResultSet queryExecutor(String query, Connection conn, String... parameters) throws SQLException {
        int index;

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        List<String> userInput = Arrays.asList(parameters);
        // Οριζουμε τι θα πρεπει να μπει στα κοματια του ερωτηματος που εχουν το ερωτηματικο "?".
        for (String input : userInput) {
            index = userInput.indexOf(input) + 1;
            System.out.println(index);
            preparedStatement.setString(index, input);
        }

        preparedStatement.executeQuery();
        // Εδω θα περιεχονται τα αποτελεσματα απο την βαση,
        return preparedStatement.getResultSet();
    }

    private static HashMap<String, String> retrieveData(ResultSet fromDB, List<String> dataToRetrieve) throws SQLException {
        HashMap<String, String> retrievedData = new HashMap<>();
        fromDB.next();

        for (String retrieve : dataToRetrieve) {
            retrievedData.put(retrieve, fromDB.getString(retrieve));
        }

        return retrievedData;
    }


    public static HashMap<String, String> getFromDatabase(String username, String query, Connection conn, String table, String... retrieves) throws SQLException {
        ResultSet resultsFromDB;
        resultsFromDB = QueryManager.queryExecutor(MessageFormat.format(query, table), conn, username);

        return QueryManager.retrieveData(resultsFromDB, Arrays.asList(retrieves));
    }

}