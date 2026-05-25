package com.ead.authuser.dtos;

import com.ead.authuser.validation.UsernameConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
    @NotBlank
    @Size(min = 4, max = 50)
    // custom constraint implementation
    @UsernameConstraint
    private String username;
    @NotBlank
    @Email(message = "Invalid format")
    @Size(min = 4, max = 50)
    private String email;
    private String fullName;
    private String phoneNumber;
    @NotBlank
    private String cpf;
}
