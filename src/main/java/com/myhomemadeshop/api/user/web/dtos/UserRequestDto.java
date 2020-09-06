package com.myhomemadeshop.api.user.web.dtos;

import com.myhomemadeshop.api.user.domain.UserType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequestDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;
    @NotBlank
    @Size(min = 5)
    private String firstName;
    @NotBlank
    @Size(min = 5)
    private String lastName;
    @NotNull
    private UserType userType;

}
