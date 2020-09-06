package com.myhomemadeshop.api.company.domain.entity;

import com.myhomemadeshop.api.infrastructure.domain.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "company_activities")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CompanyActivity extends BaseEntity {

    @Column(unique = true)
    private String name;


}
