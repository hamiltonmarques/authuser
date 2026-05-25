package com.ead.authuser.exception.validation;

public class UsernameAlreadyExistsException extends AlreadyExistsException {
    public UsernameAlreadyExistsException() {
        super("Username already exists");
    }
}
