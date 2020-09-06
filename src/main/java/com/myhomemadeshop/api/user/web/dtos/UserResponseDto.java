package com.myhomemadeshop.api.user.web.dtos;

import com.myhomemadeshop.api.user.domain.UserType;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserType userType;
}
