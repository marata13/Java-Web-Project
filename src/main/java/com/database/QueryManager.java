package com.database;

import java.sql.*;
import java.sql.Date;
import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class QueryManager {

    /**
     * Αυτη η βοηθητικη μεθοδος μας επιτρεπει να εκτελουμε
     * αιτηματα προς την βαση, οτι αιτημα και να δωθει
     * στην παραμετρο, θα το εκ`τελεσει. Αυτο αρκει να
     * εχουμε δωσει το σωστο Connection και τις σωστες
     * παραμετρους.
     *
     * @param query Το ερωτημα που προκειται να εκτελεστει.
     * @param conn Η συνδεση προς την βαση.
     * @param parameters Οι παραμετροι που θα τοποθετιθουν στο ερωτημα της SQL.
     * @param isWrite Εαν ειναι για εγραφη στην βαση η για διαβασμα.
     * @return Το συνολο τον εποτελεσματων απο την βαση.
     * @throws SQLException SQL.
     */
    private static ResultSet queryExecutor(String query,
                                           Connection conn,
                                           boolean isWrite,
                                           Object... parameters) throws SQLException {
        int index;

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        List<Object> userInput = Arrays.asList(parameters);
        // Οριζουμε τι θα πρεπει να μπει στα κοματια του ερωτηματος που εχουν το ερωτηματικο "?".
        for (Object input : userInput) {
            index = userInput.indexOf(input) + 1;
            if (input instanceof String) preparedStatement.setString(index, (String) input);
            else if (input instanceof Integer) preparedStatement.setInt(index, (Integer) input);
            else if (input instanceof Date) preparedStatement.setDate(index, (Date) input);
        }

        preparedStatement.executeQuery();
        // Εδω θα περιεχονται τα αποτελεσματα απο την βαση,
        return (isWrite)? null : preparedStatement.getResultSet();
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
    private static HashMap<String, String> retrieveData(ResultSet fromDB,
                                                        List<Object> dataToRetrieve) throws SQLException {


        HashMap<String, String> retrievedData = new HashMap<>();
        if (!fromDB.next()) return new HashMap<>();


        for (Object retrieve : dataToRetrieve) {
            retrievedData.put((String) retrieve, fromDB.getString((String) retrieve));
        }

        return retrievedData;
    }

    /**
     * Σε αυτη την μεθοδο τραβαμε απο την βαση δεδομενων ολα
     * τα απαραιτητα δεδομενα.
     *
     * @param selector Το username με το οποιο θα ψαξουμε στην βαση.
     * @param query Το ερωτημα που θελουμε να εκτελεσουμε.
     * @param conn Η συνδεση που εχουμε αποκαταστησει.
     * @param table Ο πινακας απο τον οποιο θελουμε να τραβιξουμε τα δεδομενα.
     * @param retrieves Τα συγκεκριμενα δεδομενα που θελουμε να τραβιξουμε ( π.χ username και password )
     * @return Ενα HashMap<String,String> το οποιο περιεχει τις πληροφοριες που ζητηθηκαν.
     * @throws SQLException Αφου εμπλεκουμε SQL ερωτηματα.
     */
    public static HashMap<String, String> getFromDatabase(String selector,
                                                          String query,
                                                          Connection conn,
                                                          String table,
                                                          Object... retrieves) throws SQLException {


        ResultSet resultsFromDB;
        resultsFromDB = QueryManager.queryExecutor(MessageFormat.format(query, table), conn,false, selector);
        assert resultsFromDB != null;
        return QueryManager.retrieveData(resultsFromDB, Arrays.asList(retrieves));
    }

    public static void saveToDatabase(String query,
                                      Connection conn,
                                      String table,
                                      Object... fields) throws SQLException {

        QueryManager.queryExecutor(MessageFormat.format(query, table), conn, true, fields);
    }

    /**
    *Βοηθητική μέθοδος για να πάρουμε την σημερινή ημερομηνία σε μορφή sql
     */
    public static Date currentDate (LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    /**
     * @Source https://stackoverflow.com/questions/2937086/how-to-get-the-first-day-of-the-current-week-and-month/2938209
    * Βοηθητική Μέθοδος για να πάρουμε την πρώτη μέρα της τρέχουσας εβδομάδας
     * */
    public static LocalDate startOfWeek(int week) {
        return LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).plusDays(week);
    }
    //
    public static LocalDate endOfWeek() {
        return LocalDate.now()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }

   public static String dateStr(int add){
        return endOfWeek().plusDays(add).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
   }

    /**
     *Με αυτή την μέθοδο καλούμε όλα τα ραντεβού ενός ασθενή που ήταν προγραμματισμένα πριν την
     *     * σημετινή ημερομηνία
     * @param username το username του ασθενή για το οποίο θέλουμε να δούμε τα ραντεβού
     * @param conn η σύνδεση προσ την βάση δεδομένων
     * @param query το ερώτημα που εκτελείται στην βάση
     * @return επιστρέφει ένα resultSet που χρησιμοποιείται στην μέθοδο showPreviousAppointments
     * @throws SQLException
     */
    public static ResultSet getPreviousAppointments(String username,  Connection conn,String query) throws SQLException {
        PreparedStatement st = conn.prepareStatement(query);

        st.setDate(1, currentDate(java.time.LocalDate.now()));
        st.setString(2, username);
        return st.executeQuery();
    }

    public static ResultSet getDoctorAppointments(String username,  LocalDate date, Connection conn, String query) throws SQLException {
        PreparedStatement st = conn.prepareStatement(query);

        st.setDate(1, currentDate(date));
        st.setString(2, username);
        return st.executeQuery();
    }

    public static ResultSet getSpecificDoctorAppointments(String username, String name,  String surname, Connection conn, String query) throws SQLException {
        PreparedStatement st = conn.prepareStatement(query);

        st.setString(1, name);
        st.setString(2, surname);
        st.setString(3, username);
        return st.executeQuery();
    }
}