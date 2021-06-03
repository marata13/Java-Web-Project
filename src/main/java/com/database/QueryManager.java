package com.database;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;

public class QueryManager {

    /**
     * Αυτη η βοηθητικη μεθοδος μας επιτρεπει να εκτελουμε
     * αιτηματα προς την βαση, οτι αιτημα και να δωθει
     * στην παραμετρο, θα το εκτελεσει. Αυτο αρκει να
     * εχουμε δωσει το σωστο Connection και τις σωστες
     * παραμετρους.
     *
     * @param query Το ερωτημα που προκειται να εκτελεστει.
     * @param conn Η συνδεση προς την βαση.
     * @param parameters Οι παραμετροι που θα τοποθετιθουν στο ερωτημα της SQL.
     * @return Το συνολο τον εποτελεσματων απο την βαση.
     * @throws SQLException SQL.
     */
    private static ResultSet queryExecutor(String query, Connection conn, String... parameters) throws SQLException {
        int index;

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        List<String> userInput = Arrays.asList(parameters);
        // Οριζουμε τι θα πρεπει να μπει στα κοματια του ερωτηματος που εχουν το ερωτηματικο "?".
        for (String input : userInput) {
            index = userInput.indexOf(input) + 1;
            preparedStatement.setString(index, input);
        }

        preparedStatement.executeQuery();
        // Εδω θα περιεχονται τα αποτελεσματα απο την βαση,
        return preparedStatement.getResultSet();
    }

    /**
     * Σε αυτη την μεθοδο τοποθετουμε τα στοιχεια
     * που εχουμε λαβει απο την βαση μεσα σε ενα
     * HashMap.
     *
     * @param fromDB Το συνολο των δεδομενων που λαβαμε απο την βαση.
     * @param dataToRetrieve Τα δεδομενα που θελουμε να τραβιξουμε.
     * @return Το HashMap με τα δεδομενα.
     * @throws SQLException SQL
     */
    private static HashMap<String, String> retrieveData(ResultSet fromDB, List<String> dataToRetrieve) throws SQLException {
        HashMap<String, String> retrievedData = new HashMap<>();
        fromDB.next();

        for (String retrieve : dataToRetrieve) {
            retrievedData.put(retrieve, fromDB.getString(retrieve));
        }

        return retrievedData;
    }


    /**
     * Σε αυτη την μεθοδο τραβαμε απο την βαση δεδομενων ολα
     * τα απαραιτητα δεδομενα.
     *
     * @param username Το username με το οποιο θα ψαξουμε στην βαση.
     * @param query Το ερωτημα που θελουμε να εκτελεσουμε.
     * @param conn Η συνδεση που εχουμε αποκαταστησει.
     * @param table Ο πινακας απο τον οποιο θελουμε να τραβιξουμε τα δεδομενα.
     * @param retrieves Τα συγκεκριμενα δεδομενα που θελουμε να τραβιξουμε ( π.χ username και password )
     * @return Ενα HashMap<String,String> το οποιο περιεχει τις πληροφοριες που ζητηθηκαν.
     * @throws SQLException Αφου εμπλεκουμε SQL ερωτηματα.
     */
    public static HashMap<String, String> getFromDatabase(String username, String query, Connection conn, String table, String... retrieves) throws SQLException {
        ResultSet resultsFromDB;
        resultsFromDB = QueryManager.queryExecutor(MessageFormat.format(query, table), conn, username);

        return QueryManager.retrieveData(resultsFromDB, Arrays.asList(retrieves));
    }

}