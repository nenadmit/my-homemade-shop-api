package com.myhomemadeshop.api.user.domain;

import com.myhomemadeshop.api.company.domain.entity.Company;
import com.myhomemadeshop.api.infrastructure.domain.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

  private String email;
  private String password;
  private String firstName;
  private String lastName;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  private LocalDateTime dateRegistered;
  private Boolean banned;
  private Boolean activated;

  @OneToOne(mappedBy = "user")
  private Company company;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @PrePersist
  void init() {
    this.dateRegistered = LocalDateTime.now();
    this.banned = false;
    this.activated = true;
  }
}
