package com.core.system.systemUsers;

import com.core.exceptions.LoginFailure;
import com.core.security.SecurityManager;
import com.database.Database;
import com.database.QueryManager;
import com.database.queries.Queries;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
    protected static int usersCounter = 0;

    /**
     * @param username the username.
     * @param password the password of the user.
     * @param name the name of the user.
     * @param surname the surname of the user.
     */
    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User(){
    }


    public abstract HashMap<String, String> getUserDetails (String username, String table) throws SQLException;

    /**
     *
     * @param username the username to connect.
     * @param password the password to connect.
     * @throws LoginFailure in case of login failure.
     */
    public void login(String username,
                      String password,
                      String table) throws LoginFailure, SQLException, NoSuchAlgorithmException {

        HashMap<String, String> dbCredentials = new HashMap<>();

        dbCredentials = QueryManager.getFromDatabase(
                username,
                Queries.RETRIEVE_CREDENTIALS.query,
                Database.getConnection(),
                table,
                "username",
                "password"
        );

        String fromDBUsername = dbCredentials.get("username");
        String fromDBPassword = dbCredentials.get("password");
        String hashedPassword = SecurityManager.getHash(password);

        if (!(username.equals(fromDBUsername) && hashedPassword.equals(fromDBPassword))) {
            throw new LoginFailure("Login Failed...");
        }
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response, String file) throws IOException {
        String contextPath = request.getContextPath();
        request.getSession().invalidate();
        //session.getAttribute("username");
        response.sendRedirect(contextPath+"/"+file);
    }
}