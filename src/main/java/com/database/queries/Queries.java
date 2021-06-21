package com.database.queries;

public enum Queries {

    RETRIEVE_CREDENTIALS("SELECT username,password FROM {0} WHERE username = ?"),

    RETRIEVE_DETAILS("SELECT * FROM {0} WHERE username = ?"),

    PREVIOUS_APPOINTMENTS("select date,time,doctor_name,doctor_surname,doctor_specialty from appointment where date< ? and patient_username= ? order by date"),

    DOCTOR_APPOINTMENTS("select date,time,patient_name,patient_surname from appointment where date= ? and doctor_amka= ? order by time"),

    SPECIFIC_DOCTOR_APPOINTMENTS("select date,time,patient_name,patient_surname from appointment where patient_name= ? and patient_surname=? and doctor_amka= ? order by date"),

    SAVE_PATIENT("INSERT INTO patient(patient_amka,username,password,name,surname) VALUES (?, ?, ?, ?, ?);");


    public final String query;

    Queries(String query) { this.query = query; }

}
