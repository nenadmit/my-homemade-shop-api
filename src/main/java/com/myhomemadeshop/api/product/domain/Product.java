package com.myhomemadeshop.api.product.domain;

import com.myhomemadeshop.api.company.domain.entity.Company;
import com.myhomemadeshop.api.infrastructure.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private double price;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductPriceType priceType;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
