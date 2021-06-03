package com.database.queries;

public enum Queries {

    RETRIEVE_CREDENTIALS("SELECT username,password FROM {0} WHERE username = ?"),

    RETRIEVE_DETAILS("SELECT * FROM {0} WHERE username = ?"),

    RETRIEVE_APPOINTMENTS("SELECT * FROM appointments WHERE {0} = ?");

    public final String query;
    Queries(String query) {
        this.query = query;
    }
}
