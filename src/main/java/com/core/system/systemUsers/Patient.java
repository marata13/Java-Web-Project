package com.core.system.systemUsers;

import com.core.system.management.Appointment;
import com.database.Database;
import com.database.QueryManager;
import com.database.queries.Queries;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * This class refers to the Patients.
 */
public class Patient extends User {
    private String amka;
    private Appointment appointment;
    private boolean isLock;

    /**
     *
     * @param username The username of the patient.
     * @param password The password of the patient.
     * @param name The name of the patient.
     * @param surname The surname of the patient.
     * @param amka The amka of the patient.
     * @param appointment The appointments.
     */
    public Patient(String username, String password, String name, String surname, String amka, boolean isLock, Appointment appointment) {
        super(username, password, name, surname);
        this.amka = amka;
        this.appointment = appointment;
        this.isLock = isLock;
    }

    public Patient(){
        super();
    }

    @Override
    public HashMap<String, String> getUserDetails(String username, String table) throws SQLException {
        return QueryManager.getFromDatabase(
                username,
                Queries.RETRIEVE_DETAILS.query,
                Database.getConnection(),
                table,
                "patient_amka",
                "username",
                "name",
                "surname"
        );
    }

    /**
     * @return The amka.
     */
    public String getAmka(){
        return amka;
    }

    /**
     * Looking for a specific doctor using his name.
     * @param doctorName The name of the doctor we are looking for.
     */
    public void searchAppointmentByDoctorName(String doctorName){
        if (!isLock) appointment.showAppointmentsByDoctorName(doctorName);
    }

    /**
     * Looking for all the doctors with the specialty.
     * @param doctorSpecialty The doctors specialty.
     */
    public void searchAppointmentByDoctorSpecialty(String doctorSpecialty){
        if (!isLock) appointment.showAppointmentsBySpecialty(doctorSpecialty);
    }

    /**
     * display the appointments.
     */
    public void showScheduledAppointments(){
        if (!isLock) appointment.showPatientAppointment(this);
    }

    /**
     * display the appointments history.
     */
   /*
   *old unused method that changed

    public void showAppointmentsHistory(){
        if (!isLock) appointment.showPatientHistory(this);
    }*/
}
