package com.core.system.management;

import com.core.system.systemUsers.Doctor;
import com.core.system.systemUsers.Patient;
import com.database.Database;
import com.database.queries.Queries;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    /**
     * This method creates a new appointment
     * @param doctor The doctor who will have the appointment.
     * @param patient The patient who wants the appointment.
     * @param time The scheduled time for the appointment.
     */
    public void makeAppointment(Doctor doctor, Patient patient, String time) {
        if(doctor.getSchedule().get(time) == null) {
            doctor.getSchedule().put(time, patient); // making an appointment.
            if(!appointments.contains(doctor.getSchedule())) this.appointments.add(doctor.getSchedule());
        }
        else System.out.println("You cannot schedule an appointment on " + time + ". Please, choose another date!");
    }

    /**
     *
     * @param doctor The doctor to whom the appointment had been arranged.
     * @param patient The patient who did / canceled it.
     * @param time What time would the appointment be.
     */
    public void removeAppointment(Doctor doctor, Patient patient, String time) {
        /*if(doctor.isLoggedIn()){
            for(HashMap<String, Patient> Schedule : appointments){
                if(Schedule.containsValue(patient)) {
                    for (String s : Schedule.keySet())  {
                        if(Schedule.get(s) == patient && s.equals(time)) {
                            int day = Integer.parseInt(s.split("/")[0]);
                            int month = Integer.parseInt(s.split("/")[1]);
                            int year = Integer.parseInt(s.split("/")[2]);
                            Date scheduledDay = new Date(year, month, day);
                            Date currentDay = new Date();
                            long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
                            if(scheduledDay.getTime() > currentDay.getTime() + 3 * MILLIS_IN_A_DAY){
                                doctor.getSchedule().remove(time); // remove an appointment.
                                break;
                            }
                        }
                    }
                }
            }
        }*/
    }

    /**
     * In this method we display all appointments of a specific doctor for a specific day
     * @param doctorAMKA The AMKA of the doctor we are interested in learning his program.
     * @param date The date on which the doctor wants to see his program.
     */
    public static void showAppointmentsPerDay(String doctorAMKA,  String date, PrintWriter out) throws SQLException, IOException {
        Long doctor_amka = Long.parseLong(doctorAMKA);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(date, formatter);
        Connection conn = Database.getConnection();
        ResultSet rs= getDoctorAppointments(doctor_amka, date1, conn, Queries.DOCTOR_APPOINTMENTS.query);

        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCount = rsMeta.getColumnCount();
        out.println("<TABLE BORDER=1>");
        out.println("<TR>");
        for(int i=1;i<=columnCount;i++) {
            out.print("<TH>"+rsMeta.getColumnName(i));
        }
        out.println();


        while(rs.next()) {
            out.println("<TR>");
            for(int i=1;i<=columnCount;i++) {
                out.print("<TD>"+rs.getString(i));
            }
            out.println();
        }
        out.println();
        conn.close();
    }

    /**Display program for a sequence of days starting from the specified date
     * @param doctorAMKA The AMKA of the doctor we want of whom the program we want to show
     * @param starting_date The date from when we start showing the program
     * @param out The jsp writer
     */
    public static void showAppointmentPerWeek(long doctorAMKA, LocalDate starting_date,PrintWriter out) throws SQLException {
        Connection conn = Database.getConnection();
        ResultSet rs= getDoctorAppointmentsPerWeek(starting_date,doctorAMKA,conn, Queries.DOCTOR_APPOINTMENT_PER_WEEK.query);

        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCount = rsMeta.getColumnCount();
        out.println("<TABLE BORDER=1>");
        out.println("<TR>");
        for(int i=1;i<=columnCount;i++) {
            out.print("<TH>"+rsMeta.getColumnName(i));
        }
        out.println();


        while(rs.next()) {
            out.println("<TR>");
            for(int i=1;i<=columnCount;i++) {
                out.print("<TD>"+rs.getString(i));
            }
            out.println();
        }
        out.println();
        conn.close();
    }


    /**
     * This method finds a specific doctor appointment.
     * @param name The patient's name.
     * @param surname The patient's surname.
     */
    public static void showAppointmentDoctorSide(String doctorAMKA, String name, String surname, JspWriter out) throws SQLException, IOException {
        Long doctor_amka = Long.parseLong(doctorAMKA);
        Connection conn = Database.getConnection();
        ResultSet rs= getSpecificDoctorAppointments(doctor_amka, name, surname, conn, Queries.SPECIFIC_DOCTOR_APPOINTMENTS.query);

        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCount = rsMeta.getColumnCount();
        out.println("<TABLE>");
        out.println("<TABLE BORDER=1>");
        out.println("<TR>");
        for(int i=1;i<=columnCount;i++) {
            out.print("<TH>"+rsMeta.getColumnName(i));
        }
        out.println();


        while(rs.next()) {
            out.println("<TR>");
            for(int i=1;i<=columnCount;i++) {
                out.print("<TD>"+rs.getString(i));
            }
            out.println();
        }
        out.println();
        out.println("</TABLE>");
        conn.close();
    }

    /**
     * This method finds a specific patient appointment.
     * @param doctor The doctor we are interested in.
     * @param time The time that the patient has closed.
     */
    public static void showAppointmentPatientSide(Doctor doctor, String time) {
        //if(doctor.getSchedule().containsKey(time) && doctor.getSchedule().get(time) != null)  System.out.println(time + " " + doctor.getSchedule().get(time).getName());
        //else System.out.println("There is no scheduled appointment on " + time);
    }

    /**
     * This method displays the scheduled
     * appointments of a patient.
     * @param patient The patient we are interested in.
     */
    public void showPatientAppointment(Patient patient) {
        boolean find = false;
        // find the patient if exists.
        for(HashMap<String, Patient> Schedule : appointments){
            if(Schedule.containsValue(patient)) {
                for (String s : Schedule.keySet())  {
                    if(Schedule.get(s) == patient) {
                        System.out.println("You have scheduled an appointment on " + s);
                        find = true;
                        break;
                    }
                }
            }
        }
        if(!find) System.out.println("Oops! You forgot to schedule an appointment! Don't hesitate to contact your doctor so we can see you again!");
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
        out.println("<TR>");
        for(int i=1;i<=columnCount;i++) {
            out.print("<TH>"+rsMeta.getColumnName(i));
        }
        out.println();


        while(rs.next()) {
            out.println("<TR>");
            for(int i=1;i<=columnCount;i++) {
                out.print("<TD>"+rs.getString(i));
            }
            out.println();
        }
        out.println();
        conn.close();
    }

    /**
     * This method displays the program of the
     * doctor we want based on his name.
     * @param doctorName The doctor we need.
     */
    public void showAppointmentsByDoctorName(String doctorName) {
        for(Doctor doctor : doctors) {
           /* if(doctor.getName().equals(doctorName)) {
                for(String s : doctor.getSchedule().keySet()) {
                    if(doctor.getSchedule().get(s) == null) System.out.println(s);
                }
                break;
            }*/
        }
    }

    /**
     * In this method we show all the doctors
     * who have a specific specialty.
     * @param doctorSpecialty The specialty we are looking for.
     */
    public void showAppointmentsBySpecialty(String doctorSpecialty) {
        for(Doctor doctor : doctors) {
            if(doctor.getSpecialty().equals(doctorSpecialty)) {
                for(String s : doctor.getSchedule().keySet()) {
                    //if(doctor.getSchedule().get(s) == null) System.out.println(doctor.getName() + " " + s);
                }
                break;
            }
        }
    }

    public static void showNextAppointmentsAndDelete(String username, JspWriter out) throws SQLException, IOException {
        Connection conn = Database.getConnection();
        ResultSet rs= getNextAppointmentsAndDelete(username,conn, Queries.NEXT_APPOINTMENTS.query);

        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCount = rsMeta.getColumnCount();
        out.println("<TR>");
        for(int i=2;i<=columnCount;i++) {
            out.print("<TH>"+rsMeta.getColumnName(i));
        }out.print("<TH>Delete");
        out.println();


        while(rs.next()) {
            out.println("<TR>");
            for(int i=2;i<=columnCount;i++) {
                out.print("<TD>"+rs.getString(i));
            }if(rs.getDate(2).compareTo(currentDate(java.time.LocalDate.now().plusDays(3)))>=0){

                out.print("<TD><form  name=\"DeleteAppointments\" action=\"/Kotza_Project_Web_war/DeleteAppointments\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"appointmentID\" value=\"" +rs.getInt(1) + "\">" +
                        "<input type=\"submit\" id = \"deleteButton\" value=\"delete\"></form>");
            }
            out.println();
        }
        out.println();
        conn.close();
    }
}
