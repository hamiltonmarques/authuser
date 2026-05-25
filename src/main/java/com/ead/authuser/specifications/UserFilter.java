package com.ead.authuser.specifications;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {
    private UserType type;
    private UserStatus status;
    private String email;
}