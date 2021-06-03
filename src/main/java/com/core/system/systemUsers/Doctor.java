package com.core.system.systemUsers;

import com.core.system.management.Appointment;
import com.database.Database;
import com.database.QueryManager;
import com.database.queries.Queries;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *  This class refers to the doctors.
 */
public class Doctor extends User {
    private String specialty;
    private Appointment appointment;
    private HashMap<String, Patient> schedule;
    private List<String> times;

    /**
     *
     * @param username The username of the doctor.
     * @param password The password of the doctor.
     * @param name  The name of the doctor.
     * @param surname The surname of the doctor.
     * @param specialty The specialty of the doctor.
     * @param appointment The appointments.
     */
    public Doctor(String username, String password, String name, String surname, String specialty, Appointment appointment) {
        super(username, password, name, surname);
        this.specialty = specialty;
        this.schedule = new HashMap<>();
        this.times = new ArrayList<>();
        this.appointment = appointment;
    }
    public Doctor() {
        super();
    }

    @Override
    public HashMap<String, String> getUserDetails(String username, String table) throws SQLException {
        return QueryManager.getFromDatabase(
                username,
                Queries.RETRIEVE_DETAILS.query,
                Database.getConnection(),
                table,
                "doctorAMKA",
                "username",
                "specialty",
                "surname",
                "name"
        );
    }

    /**
     * We allow to change the specialty.
     * @param specialty new specialty
     */
    public void setSpecialty(String specialty){
        this.specialty = specialty;
    }

    /**
     * @return the specialty of the doctor.
     */
    public String getSpecialty() {
        return this.specialty;
    }

    /**
     *
     * @return Doctor appointments.
     */
    public HashMap<String, Patient> getSchedule(){
        return this.schedule;
    }

    public void availability() {
        for (String currTime : schedule.keySet()) {
            if(schedule.get(currTime) != null) {
                System.out.println(currTime+" closed.");
            }
        }
    }

    /**
     * @param time the time that the doctor didn't need to have appointments.
     */
    public void setDnd(String time) {
        Patient fake = new Patient("","","","","",true,appointment);
        if (schedule.containsKey(time)) {
            appointment.makeAppointment(this,fake,time);
        }
        else {
            System.out.println("This time does not exist.");
        }
    }

    public void replaceDnd(Patient toReplace,String time) {
        if (schedule.containsKey(time)) {
            schedule.put(time,toReplace);
        }
    }

    public void generateDoctorSchedule() {
        generateSchedule();
        generateTimes();
    }

    private void generateSchedule() {

        long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date tmp = null;
        Date support = new Date();
        for (int day = 1; day <= 30; day++) {
            tmp = new Date(support.getTime() + MILLIS_IN_A_DAY * day);
            times.add(formatter.format(tmp).toString());
        }
    }

    private void generateTimes() {
        for (String s : times) {
            for (int i = 0; i < 17; i++) {
                schedule.put(s + " " + Integer.toString(i) +":00", null);
            }
        }
        times.clear();
    }

    /**
     * display doctor's program.
     */
    public void program() {
        appointment.showAllAppointments(this);
    }

    /**
     * Display a specific appointment.
     * @param time The time of the appointment.
     */
    public void searchAppointment(String time) {
        appointment.showAppointment(this, time);
    }

    public void removeAppointment(String time) {
        if (schedule.get(time) == null) return;

        Patient tmpPatient = schedule.get(time);
        appointment.removeAppointment(this, tmpPatient, time);
    }


}
