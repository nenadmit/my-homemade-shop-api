package com.myhomemadeshop.api.company.web.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CompanyRequestDto {

    @NotNull
    @Size(min = 4,max = 25, message = "Name size cannot be less than 4 and greater than 25 characters")
    private String name;
    @Size(min = 4,max = 25, message = "Tagline size cannot be less than 4 and greater than 20 characters")
    private String tagline;
    @Size(min = 10,max = 100, message = "About Us size cannot be less than 4 and greater than 100 characters")
    private String aboutUs;
    @NotNull(message = "User Id cannot be null")
    private Long userId;

}
