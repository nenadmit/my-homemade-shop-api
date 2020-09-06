package com.myhomemadeshop.api.user.web.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
