package com.ead.authuser.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDTO {
    @NotBlank
    @Size(min = 8, max = 20)
    private String oldPassword;
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
}
