package com.core.system.management;

import com.core.system.systemUsers.Doctor;
import com.core.system.systemUsers.Patient;
import com.database.Database;
import com.database.queries.Queries;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

import static com.database.QueryManager.*;


/**
 * This class is responsible for all the
 * functions that must be performed, so
 * that an appointment can be arranged.
 */
public class Appointment {
    final private HashSet<HashMap<String, Patient>> appointments;
    final private HashSet<Doctor> doctors;

    /**
     * In the constructor we create two new instances
     * of the HashMap and HasSet objects which we use
     * to identify appointments and doctors.
     */
    public Appointment() {
        this.appointments = new HashSet<>();
        this.doctors = new HashSet<>();
    }

    /**Display program for a sequence of days starting from the specified date
     * @param doctorAMKA The AMKA of the doctor we want of whom the program we want to show
     * @param starting_date The date from when we start showing the program
     * @param out The jsp writer
     */
    public static void showAppointmentPerWeek(long doctorAMKA, LocalDate starting_date,PrintWriter out) throws SQLException, IOException {
        Connection conn = Database.getConnection();
        ResultSet rs= getDoctorAppointmentsPerWeek(starting_date,doctorAMKA,conn, Queries.DOCTOR_APPOINTMENT_PER_WEEK.query);

        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCount = rsMeta.getColumnCount();
        out.println("<TABLE BORDER=1>");
        generateTable(out,rs,rsMeta,columnCount);
        out.println("</table>");
        conn.close();
    }


    /**
     * This method finds a specific doctor appointment.
     * @param name The patient's name.
     * @param surname The patient's surname.
     */
    public static void showForSpecificPatient(String doctorAMKA, String name, String surname, PrintWriter out) throws SQLException, IOException {
        Long doctor_amka = Long.parseLong(doctorAMKA);
        Connection conn = Database.getConnection();
        ResultSet rs= getSpecificDoctorAppointments(doctor_amka, name, surname, conn, Queries.SPECIFIC_DOCTOR_APPOINTMENTS.query);

        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCount = rsMeta.getColumnCount();
        out.println("<TABLE border=\"1\">");
        generateTable(out, rs, rsMeta, columnCount);
        out.println("</TABLE>");
        conn.close();
    }

    private static void generateTable(JspWriter out, ResultSet rs, ResultSetMetaData rsMeta, int columnCount) throws IOException, SQLException {
        if(!rs.isBeforeFirst()){

            out.print("<h1>No available appointments have been found<h1>");
        }else {
            out.println("<TR>");
            for (int i = 1; i <= columnCount; i++) {
                out.print("<TH>" + rsMeta.getColumnName(i));
            }
            out.println();


            while (rs.next()) {
                out.println("<TR>");
                for (int i = 1; i <= columnCount; i++) {
                    out.print("<TD>" + rs.getString(i));
                }
                out.println();
            }
        }
    }

    private static void generateTable(PrintWriter out, ResultSet rs, ResultSetMetaData rsMeta, int columnCount) throws IOException, SQLException {
        if(!rs.isBeforeFirst()){

            out.print("<h1>No available appointments have been found<h1>");
        }else {
            out.println("<TR>");
            for (int i = 1; i <= columnCount; i++) {
                out.print("<TH>" + rsMeta.getColumnName(i));
            }
            out.println();


            while (rs.next()) {
                out.println("<TR>");
                for (int i = 1; i <= columnCount; i++) {
                    out.print("<TD>" + rs.getString(i));
                }
                out.println();
            }
        }
    }

    /**
     * Αυτή η μέθοδος παίρνει το ResultSet με τα ραντεβού του ασθενή
     * και παράγει ένα html table όπου προβάλει τα αποτελέσματα
     * για χρήση σε jsp σελίδες
     * @param username το username για τον οποίο θέλουμε να δούμε τα ραντεβού
     * @param out JspWriter για να τυπώσουμε τα αποτελέσματα σε μια σελίδα jsp
     * @throws SQLException
     * @throws IOException
     */
    public static void showPreviousAppointments(String username, JspWriter out) throws SQLException, IOException {
        Connection conn = Database.getConnection();
        ResultSet rs= getPreviousAppointments(username,conn, Queries.PREVIOUS_APPOINTMENTS.query);

        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCount = rsMeta.getColumnCount();
        generateTable(out,rs,rsMeta,columnCount);
        conn.close();
    }

    public static void showNextAppointmentsAndDeleteForDoctor(Long amka, JspWriter out) throws SQLException, IOException {
        Connection conn = Database.getConnection();
        ResultSet rs= getNextAppointmentsAndDeleteForDoctor(amka,conn, Queries.NEXT_APPOINTMENTS_FOR_DOCTOR.query);
        generateDeleteTable(out, conn, rs);
    }

    public static void showNextAppointmentsAndDeleteForPatient(String username, JspWriter out) throws SQLException, IOException {
        Connection conn = Database.getConnection();
        ResultSet rs= getNextAppointmentsAndDeleteForPatient(username,conn, Queries.NEXT_APPOINTMENTS_FOR_PATIENT.query);
        generateDeleteTable(out, conn, rs);
    }

    private static void generateDeleteTable(JspWriter out, Connection conn, ResultSet rs) throws SQLException, IOException {
        if(!rs.isBeforeFirst()){

            out.print("<h1>No available appointments have been found<h1>");
        }else{
        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCount = rsMeta.getColumnCount();
        out.println("<TR>");
        for(int i=2;i<=columnCount;i++) {
            out.print("<TH>"+rsMeta.getColumnName(i));
        }out.print("<TH>Delete");
        out.println();

        while(rs.next()) {
            out.println("<TR>");
            for (int i = 2; i <= columnCount; i++) {
                out.print("<TD>" + rs.getString(i));
            }
            if (rs.getDate(2).compareTo(sqlDateConverter(LocalDate.now().plusDays(3))) >= 0) {

                out.print("<TD><form  name=\"DeleteAppointments\" action=\"/Kotza_Project_Web_war/DeleteAppointments\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"appointmentID\" value=\"" + rs.getInt(1) + "\">" +
                        "<input type=\"submit\" id = \"deleteButton\" value=\"delete\"></form>");
            }
            out.println();
        }
        }
        out.println();
        conn.close();
    }

    public static void makeAppointmentBySpeciality( PrintWriter out
            ,String patient_username,String patient_surname
            ,String patient_name, String doctorSpecialty) throws SQLException {
        Connection conn = Database.getConnection();
        ResultSet rs= getAvailableAppointments(conn,doctorSpecialty, Queries.AVAILABLE_APPOINTMENTS.query);
        if(!rs.isBeforeFirst()){

              out.print("<h1>No available appointments have been found<h1>");
             }else{

        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCount = rsMeta.getColumnCount();
        out.println("<TABLE BORDER=1>");
        out.println("<TR>");
        for(int i=2;i<=columnCount;i++) {
            out.print("<TH>"+rsMeta.getColumnName(i));
        }out.print("<TH>Make appointment");
        out.println();



        while(rs.next()) {
            out.println("<TR>");
            for(int i=2;i<=columnCount;i++) {
                out.print("<TD>"+rs.getString(i));
            }
                out.print("<TD><form  name=\"makeAppointments\" action=\"/Kotza_Project_Web_war/MakeAppointments\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"username\" value=\""+patient_username+"\">" +
                        "<input type=\"hidden\" name=\"surname\" value=\""+patient_surname+"\">" +
                        "<input type=\"hidden\" name=\"name\" value=\""+patient_name+"\">" +
                        "<input type=\"hidden\" name=\"appointmentID\" value=\"" +rs.getInt(1) + "\">" +
                        "<input type=\"submit\" id = \"makeAppointmentButton\" value=\"make appointment\"></form>");

            out.println();
        }
        }
        out.println("</TABLE>");
        conn.close();
    }
}
