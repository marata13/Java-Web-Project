package com.database.queries;

public enum Queries {

    RETRIEVE_CREDENTIALS("SELECT username,password FROM {0} WHERE username = ?"),

    RETRIEVE_USERNAME("SELECT username FROM {0} WHERE username = ?"),

    RETRIEVE_AMKA("SELECT patient_amka FROM {0} WHERE patient_amka = ?"),

    RETRIEVE_DETAILS("SELECT * FROM {0} WHERE username = ?"),

    PREVIOUS_APPOINTMENTS("select date,time,doctor_name,doctor_surname,doctor_specialty from appointment where date< ? and patient_username= ? order by date"),

    DOCTOR_APPOINTMENTS("select date,time,patient_name,patient_surname from appointment where date= ? and doctor_amka= ? order by time"),

    SPECIFIC_DOCTOR_APPOINTMENTS("select date,time,patient_name,patient_surname from appointment where patient_name= ? and patient_surname=? and doctor_amka= ? order by date");


    public final String query;

    Queries(String query) { this.query = query; }

}
