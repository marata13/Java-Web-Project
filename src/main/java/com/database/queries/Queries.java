package com.database.queries;

public enum Queries {

    RETRIEVE_CREDENTIALS("SELECT username,password FROM {0} WHERE username = ?"),

    RETRIEVE_DETAILS("SELECT * FROM {0} WHERE username = ?"),

    PREVIOUS_APPOINTMENTS("select date,time,doctor_name,doctor_surname,doctor_specialty from appointment where date< ? and patient_username= ? order by date");

    public final String query;

    Queries(String query) {
        this.query = query;
    }

}
