package com.core.system.systemUsers;

import com.core.exceptions.LoginFailure;

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
        return this.username;
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
    public void login(String username, String password) throws LoginFailure {
        if (username.equals(this.username) && password.equals(this.password)) {
            login = true;
        }
        else {
            throw new LoginFailure("The username or the password not match.");
        }
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