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

}
