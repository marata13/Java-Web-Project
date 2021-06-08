package com.core.exceptions;


/**
 *  Exception in case of login failure.
 */
public class LoginFailure extends Exception {

    public LoginFailure(String reason) {
        super(reason);
    }
}
