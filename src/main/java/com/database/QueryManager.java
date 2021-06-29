package com.database;

import java.math.BigInteger;
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
     * Η μεθοδος queryExecutor ειναι φτιαγμενει ετσι ωστε
     * να μπορει να εκτελεσει οποιοδηποτε ερωτημα προς την βαση
     * δεδομενων.
     *
     * Αυτο το καταφερνει με την χρηση μιας δυνατοτητας που μας δινει
     * η java η οποια αποκαλειτε varargs ( τα οποια συμβολιζονται ως '...' )
     * τα οποια μας επιτρεπουν να δωσουμε ως εισοδο σε μια μεθοδο εναν
     * οποιονδηποτε αριθμο απο τιμες ενος συγκεκριμενου τυπου.
     *
     * [ Τεχνηκες λεπτομεριες ]
     *
     * Στην αρχη της μεθοδου οριζουμε ενα αντικειμενο τυπου
     * PrepareStatement το οποιο λαμβανει την τιμη του απο
     * ενα reference το οποιο επιστρεφετε απο την συνδεση
     * προς την βαση. Γενικα το PrepareStatement μας βοηθαει
     * ετσι ωστε να ειναι αδυνατο να γινει καποιο SQL Injection
     * προς την βαση μας.
     *
     * Η γενικη μορφη ενος ερωτηματος που θα μπει ως εισοδο σε ενα
     * PrepareStatement ειναι η εξης:
     *      1) SELECT <ζητουμενα> FROM table WHERE <πεδιο> = <?>
     *      2) INSERT ΙΝΤΟ table (<πεδια...>) VALUES (<?, ?,? ... ,?>);
     * Οπου εχει το ερωτηματικο <?> υπονοει οτι εκει θα πρεπει το PrepareStatement
     * να παει και να βαλει τα καταλληλα δεδομενα για το συγκεκριμενο ερωτημα.
     * Τα ερωτηματα αντικαθιστανται απο τιμες με την χρηση των μεθοδων:
     *      setInt(int), setString(int), setDate(int)... κλπ.
     * οπου ολες αυτες παιρνουν ως παραμετρο εναν αριθμο ο οποιος αντιστοιχει
     * στην τοποθεσια του καθε ερωτηματικου, ξεκινοντας απο το 1.
     *
     * Η παρακατω μεθοδος προκειμενου να μπορει να κανει την παραπανω
     * διαδικασια για καθε ερωτημα, αυτο που κανει ειναι πως φτιαχνει
     * μια λιστα με βαση τa varargs ( parameters ) που παιρνει στην
     * εισοδο και ιστερα προσπαθει με μια for να καταφερει να αντιστοιχησει
     * ολες τις τιμες σε αυτην την λιστα σε καθε ενα απο τα αντιστοιχα
     * ερωτηματικα που βρισκονται στο ερωτημα της SQL. Μια επιπλεον
     * λεπτομερια ειναι πως επειδη ο τυπος των varargs ειναι τυπου
     * Object, μας επιτρεπετε να λαμβανουμε οτιδηποτε τυπο θελουμε
     * και μετα να ελεγχουμε με τον τελεστη instanceof απο που ανοικει.
     * Στην συγκεκριμενη εχουν φτιαχτει οι συνθηκες μονο για τρεις
     * τυπους, οι οποιοι ειναι και αυτοι που χρησιμοποιουμε στην
     * εν λογο ασκηση για να εκτελεσουμε ερωτηματα προς την βαση.
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
        int index; // Δεικτης προς την λιστα με τα δεδομενα που πρεπει να παρει/βαλει στην βαση.

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        List<Object> userInput = Arrays.asList(parameters);
        // Οριζουμε τι θα πρεπει να μπει στα κοματια του ερωτηματος που εχουν το ερωτηματικο "?".
        for (Object input : userInput) {
            index = userInput.indexOf(input) + 1; // Κανουμε το +1 γιατι η λιστα ξεκιναει απο 0 αλλα τα ερωτηματικα απο 1.
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
    public static LocalDate startOfWeek(int add) {
        return LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).plusDays(add);
    }
    //
    public static String endOfWeek(int add) {
        return LocalDate.now()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).plusDays(add).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

    public static ResultSet getDoctorAppointments(Long doctor_amka, LocalDate date, Connection conn, String query) throws SQLException {
        PreparedStatement st = conn.prepareStatement(query);

        st.setDate(1, currentDate(date));
        st.setLong(2, doctor_amka);
        System.out.println("return");
        return st.executeQuery();
    }

    public static ResultSet getSpecificDoctorAppointments(Long doctor_amka, String name,  String surname, Connection conn, String query) throws SQLException {
        PreparedStatement st = conn.prepareStatement(query);

        st.setString(1, name);
        st.setString(2, surname);
        st.setLong(3, doctor_amka);
        return st.executeQuery();
    }
    public static ResultSet getDoctorAppointmentsPerWeek(LocalDate starting_date,long doctor_amka, Connection conn , String query) throws SQLException {
        PreparedStatement st = conn.prepareStatement(query);

        st.setDate(1,currentDate(starting_date));
        st.setDate(2,currentDate(starting_date.plusDays(6)));
        st.setLong(3, doctor_amka);
        return st.executeQuery();
    }
}