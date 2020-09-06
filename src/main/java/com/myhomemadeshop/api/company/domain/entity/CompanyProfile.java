package com.myhomemadeshop.api.company.domain.entity;

import com.myhomemadeshop.api.infrastructure.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "company_profile")
public class CompanyProfile extends BaseEntity {


    private String manufacturerName;
    private LocalDateTime companyCreationDate;
    private String address;
    private String country;
    private String city;
    private String website;
    private String phone;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;


}
