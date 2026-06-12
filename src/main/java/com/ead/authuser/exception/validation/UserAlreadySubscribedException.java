package com.ead.authuser.exception.validation;

public class UserAlreadySubscribedException extends AlreadyExistsException {
    public UserAlreadySubscribedException() {
        super("User already subscribed");
    }
}
