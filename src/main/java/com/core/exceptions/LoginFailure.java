package com.core.exceptions;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *  Exception in case of login failure.
 */
public class LoginFailure extends Exception {

    public LoginFailure(String reason) {
        super(reason);
    }
}
