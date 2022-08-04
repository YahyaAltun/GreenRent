package com.greenrent.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {

    @Email(message = "Please provide a correct email")
    private String email;

    @NotNull(message = "Please provide a password")
    private String password;
}
