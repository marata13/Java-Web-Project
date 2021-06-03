package com.core.system.systemUsers;

import com.database.Database;
import com.database.QueryManager;
import com.database.queries.Queries;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Admin extends User {
        List<Doctor> doctors;

        public Admin(String username, String password, String name, String surname) {
                super(username, password, name, surname);
                this.doctors = new ArrayList<>();
        }

        @Override
        public HashMap<String, String> getUserDetails(String username, String table) throws SQLException {
                return QueryManager.getFromDatabase(
                        username,
                        Queries.RETRIEVE_DETAILS.query,
                        Database.getConnection(),
                        table,
                        "username"
                );
        }

        public void addDoctor(Doctor doctor) {
                this.doctors.add(doctor);
        }

        public void removeDoctor(Doctor doctor) {
                this.doctors.remove(doctor);
        }
}
