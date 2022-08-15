package com.greenrent.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserUpdateRequest {

    @Size(max = 50)
    @NotNull(message = "Please provide your first name")
    private String firstName;

    @Size(max = 50)
    @NotNull(message = "Please provide your last name")
    private String lastName;

    @Size(min = 4, max = 20,message="Please Provide Correct Size for Password")
    @NotNull(message = "Please provide your password")
    private String password;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please provide valid phone number")
    @Size(min = 14, max = 14)
    @NotNull(message = "Please provide your phone number")
    private String phoneNumber;

    @Email(message = "Please provide valid email")
    @Size(min = 5, max = 20)
    @NotNull(message = "Please provide your email")
    private String email;

    @Size(max = 100)
    @NotNull(message = "Please provide your address")
    private String address;

    @Size(max = 15)
    @NotNull(message = "Please provide your zip code")
    private String zipCode;

    private Boolean builtIn;


    private Set<String> roles;
}
