package com.myhomemadeshop.api.company.web.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class CompanyProfileRequestDto {

    @Size(min = 4,max = 25, message = "Manufacturer Name size cannot be less than 4 and greater than 25 characters")
    private String manufacturerName;
    @Size(min = 6,max = 15, message = "Phone number size cannot be less than 6 and greater than 15 characters")
    private String phone;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime companyCreationDate;

    @Size(min = 4,max = 12, message = "Country name size cannot be less than 4 and greater than 12 characters")
    private String country;
    @Size(min = 3,max = 12, message = "City name size cannot be less than3 and greater than 12 characters")
    private String city;
    @Size(min = 4,max = 24, message = "Address size cannot be less than 4 and greater than 24 characters")
    private String address;
    @Size(min = 5,max = 35, message = "Website url size cannot be less than 5 and greater than 35 characters")
    private String website;
}
