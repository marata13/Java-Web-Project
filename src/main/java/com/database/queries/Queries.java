package com.database.queries;

public enum Queries {

    RETRIEVE_CREDENTIALS("SELECT username,password FROM {0} WHERE username = ?"),

    RETRIEVE_USERNAME("SELECT username FROM {0} WHERE username = ?"),

    RETRIEVE_AMKA("SELECT patient_amka FROM {0} WHERE patient_amka = ?"),

    RETRIEVE_DETAILS("SELECT * FROM {0} WHERE username = ?"),

    PREVIOUS_APPOINTMENTS("select date,time,doctor_name,doctor_surname,doctor_specialty from appointment where date< ? and patient_username= ? order by date"),

    DOCTOR_APPOINTMENTS("select date,time,patient_name,patient_surname from appointment where date= ? and doctor_amka= ? order by time"),

    SPECIFIC_DOCTOR_APPOINTMENTS("select date,time,patient_name,patient_surname from appointment where patient_name= ? and patient_surname=? and doctor_amka= ? order by date"),

    DOCTOR_APPOINTMENT_PER_WEEK(" select date,time,patient_name,patient_surname from appointment where date>=? and date<=? and doctor_amka=? and patient_username is not null order by (date,time)"),

    NEXT_APPOINTMENTS("select appointment_id,date,time,doctor_name,doctor_surname,doctor_specialty from appointment where date>= ? and patient_username= ? order by date"),

    DELETE_APPOINTMENTS("UPDATE appointment SET patient_username = null, patient_surname = null, patient_name = null WHERE appointment_id = ?"),

    AVAILABLE_APPOINTMENTS("select appointment_id,date,time,doctor_name,doctor_surname,doctor_specialty from appointment where doctor_specialty=? and date>=? and patient_username is null and patient_name is null and patient_surname is null"),

    MAKE_APPOINTMENTS("UPDATE appointment SET patient_username = ?, patient_surname = ?, patient_name = ? WHERE appointment_id = ?"),

    ADD_DOCTOR("INSERT INTO {0}(doctor_amka,admin_userid,username,password,specialty,surname,name) VALUES (?, ?, ? , ?, ?, ?, ?)"),

    DELETE_DOCTOR("DELETE FROM {0} WHERE doctor_amka= ? ;"),

    ADD_PATIENT("INSERT INTO {0}(patient_amka,username,password,surname,name) VALUES (?,?,?,?,?);");

    public final String query;

    Queries(String query) { this.query = query; }

}
