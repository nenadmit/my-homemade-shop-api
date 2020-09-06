package com.myhomemadeshop.api.company.domain.entity;

import com.myhomemadeshop.api.infrastructure.domain.BaseEntity;
import com.myhomemadeshop.api.product.domain.Product;
import com.myhomemadeshop.api.user.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "companies")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Company extends BaseEntity {

  private String name;
  private String tagline;
  private String aboutUs;
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
  @OneToOne(mappedBy = "company")
  private CompanyProfile companyProfile;
  @OneToMany(mappedBy = "company")
  private List<Product> products = new ArrayList<>();
  @ManyToMany
  @JoinTable(
          name = "company_company_activity",
          joinColumns = @JoinColumn(name = "company_id"),
          inverseJoinColumns = @JoinColumn(name = "company_activity_id"))
  private Set<CompanyActivity> activities = new HashSet<>();
}
