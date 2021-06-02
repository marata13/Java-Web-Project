package com.core.system.systemUsers;

import com.core.exceptions.LoginFailure;
import com.database.Database;
import com.database.QueryManager;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * This class is related to all the actions
 * that a user can do.
 */
public abstract class User {
    protected String username;
    protected String password;
    protected String name;
    protected String surname;
    protected boolean login;
    protected static int usersCounter = 0;

    /**
     * @param username the username.
     * @param password the password of the user.
     * @param name the name of the user.
     * @param surname the surname of the user.
     */
    public User(String username, String password, String name, String surname) {
        User.increaseUsers();
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.login = false;
    }

    public User(){
        this.login = false;
    }

    /**
     * @param username the new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the new Password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param surname the new surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return username
     */
    public String getUsername() {
        Connection conn;
        conn = Database.getConnection();
        return QueryManager.getUserDetails(this.username, conn, "patient").get("username");
    }

    /**
     * @return surname.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * @return userCounter
     */
    public static int getUserCounter() {
        return User.usersCounter;
    }

    /**
     *
     * @param username the username to connect.
     * @param password the password to connect.
     * @throws LoginFailure in case of login failure.
     */
    public void login(String username, String password, String table) throws LoginFailure, SQLException {
        Connection conn;
        conn = Database.getConnection();
        HashMap<String, String> dbCredentials;
        dbCredentials = QueryManager.getCredentials(username, conn, table);
        String fromDBUsername = dbCredentials.get("username");
        String fromDBPassword = dbCredentials.get("password");
        if (username.equals(fromDBUsername) && password.equals(fromDBPassword)) {
           this.login = true;
        }
        conn.close();
    }

    public void logout() {
        login = false;
    }

    public boolean isLoggedIn() {
        return login;
    }

    protected static void increaseUsers() {
        User.usersCounter++;
    }
}