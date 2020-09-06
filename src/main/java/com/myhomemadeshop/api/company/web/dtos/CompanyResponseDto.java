package com.myhomemadeshop.api.company.web.dtos;

import lombok.Data;

@Data
public class CompanyResponseDto {

    private Long id;
    private String name;
    private String tagline;
    private String aboutUs;
}
