package com.core.system.systemUsers;

import com.database.Database;
import com.database.QueryManager;
import com.database.queries.Queries;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.regex.*;

public class Admin extends User {
        List<Doctor> doctors;

        public Admin(String username, String password, String name, String surname) {
                super(username, password, name, surname);
                this.doctors = new ArrayList<>();
        }

        public Admin(){}

        @Override
        public HashMap<String, String> getUserDetails(String username, String table) throws SQLException {
                return QueryManager.getFromDatabase(
                        username,
                        Queries.RETRIEVE_DETAILS.query,
                        Database.getConnection(),
                        table,
                        "username",
                        "admin_userid"
                );
        }

        //toDo:αν βάλουμε προσωρινό πίνακα ασθενών, στην sql θα γράψουμε: create table temp_patient like patient
        public void confirmPatientRegistration(String username, String password, String confirm_password, String patient_amka){
                try{
                        HashMap<String, String> username_validation = QueryManager.getFromDatabase(username, Queries.RETRIEVE_USERNAME.query, Database.getConnection(), "patient", "username");
                        HashMap<String, String> amka_validation = QueryManager.getFromDatabase(patient_amka, Queries.RETRIEVE_AMKA.query, Database.getConnection(), "patient", "patient_amka");
                        if(!username_validation.isEmpty()){
                                System.out.println("The username you used already exists");
                        }
                        if(!amka_validation.isEmpty()){
                                System.out.println("The amka you used already exists");
                        }
                }catch (SQLException e){
                 e.printStackTrace();
                }
                if(!password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s]).{8,}")){
                        System.out.println("Must contain at least 8 characters.\n" +
                                "Must contain at least one uppercase and one lowercase letter.\n" +
                                "Must contain at least one number and one special character");
                }
                if(!password.equals(confirm_password)){
                        System.out.println("Please make sure your passwords match");
                }
        }

        public void addDoctor(Doctor doctor) {
                this.doctors.add(doctor);
        }

        public void removeDoctor(Doctor doctor) {
                this.doctors.remove(doctor);
        }
}
