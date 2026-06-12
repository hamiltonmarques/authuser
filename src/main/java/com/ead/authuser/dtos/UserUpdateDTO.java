package com.ead.authuser.dtos;

import com.ead.authuser.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private String fullName;
    private String phoneNumber;
    private String cpf;
    private UserType type;
}
