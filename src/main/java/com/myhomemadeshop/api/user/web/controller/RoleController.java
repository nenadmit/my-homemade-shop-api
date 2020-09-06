package com.myhomemadeshop.api.user.web.controller;

import com.myhomemadeshop.api.infrastructure.exception.ConflictException;
import com.myhomemadeshop.api.user.domain.Role;
import com.myhomemadeshop.api.user.domain.UserType;
import com.myhomemadeshop.api.user.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

  private final RoleRepository roleRepository;
  private static final String COMPANY_ROLE = "ROLE_" + UserType.COMPANY.name();
  private static final String CUSTOMER_ROLE = "ROLE_" + UserType.CUSTOMER.name();

  @GetMapping
  public void createRoles() {

    if (roleRepository.findRoleByName(COMPANY_ROLE).isPresent()
        || roleRepository.findRoleByName(CUSTOMER_ROLE).isPresent()) {
      throw new ConflictException("Pusi ga");
    }

    roleRepository.saveAll(Arrays.asList(new Role(COMPANY_ROLE), new Role(CUSTOMER_ROLE)));
  }
}
