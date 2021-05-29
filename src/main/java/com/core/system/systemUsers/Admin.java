package com.core.system.systemUsers;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
        List<Doctor> doctors;

        public Admin(String username, String password, String name, String surname) {
                super(username, password, name, surname);
                this.doctors = new ArrayList<>();
        }

        public void addDoctor(Doctor doctor) {
                this.doctors.add(doctor);
        }

        public void removeDoctor(Doctor doctor) {
                this.doctors.remove(doctor);
        }
}
