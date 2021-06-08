package com.core.system.management;

import com.core.system.systemUsers.Doctor;
import com.core.system.systemUsers.Patient;
import com.database.Database;
import com.database.queries.Queries;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import static com.database.QueryManager.getPreviousAppointments;

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
        if(doctor.isLoggedIn()){
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
        }
    }

    /**
     * In this method we display all appointments of a specific doctor
     * @param doctor The doctor we are interested in learning his program.
     */
    public void showAllAppointments(Doctor doctor) {
        for (String s : doctor.getSchedule().keySet()) {
            if(doctor.getSchedule().get(s) == null) System.out.println(s + " "); // free time
            //else System.out.println(s + " " + doctor.getSchedule().get(s).getName()); // closed.
        }
    }

    /**
     * This method finds a specific patient appointment.
     * @param doctor The doctor we are interested in.
     * @param time The time that the patient has closed.
     */
    public void showAppointment(Doctor doctor, String time) {
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
    public static void showPreviousAppointments(String username, javax.servlet.jsp.JspWriter out) throws SQLException, IOException {
        Connection conn = Database.getConnection();
        ResultSet rs= getPreviousAppointments(username,conn, Queries.PREVIOUS_APPOINTMENTS.query);

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
}
