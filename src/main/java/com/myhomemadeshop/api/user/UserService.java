package com.myhomemadeshop.api.user;

import com.myhomemadeshop.api.company.domain.entity.Company;
import com.myhomemadeshop.api.user.domain.Role;
import com.myhomemadeshop.api.user.domain.User;
import com.myhomemadeshop.api.user.domain.UserType;
import com.myhomemadeshop.api.user.mapper.UserMapper;
import com.myhomemadeshop.api.user.repo.RoleRepository;
import com.myhomemadeshop.api.user.repo.UserRepository;
import com.myhomemadeshop.api.company.web.dtos.CompanyRequestDto;
import com.myhomemadeshop.api.user.web.dtos.UserRequestDto;
import com.myhomemadeshop.api.user.web.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.myhomemadeshop.api.infrastructure.exception.*;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper mapper;

  public UserResponseDto persist(UserRequestDto dto) {

    if(repository.findByEmail(dto.getEmail()).isPresent()){
      throw new ConflictException(String.format("User with email %s, already present",dto.getEmail()));
    }

    Role userRole = findRoleByName("ROLE_"+dto.getUserType().name());
    User user = mapper.mapToEntity(dto);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.getRoles().add(userRole);
    repository.save(user);

    return mapper.mapToResponseDto(user);
  }

    private Role findRoleByName(String roleName) {

    return roleRepository
        .findRoleByName(roleName)
        .orElseThrow(() -> new EntityNotFoundException("Role not found: " + roleName));
  }

  public User findEntityByEmail(String email) {

    return repository
        .findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User with email %s, not found."));
  }
}
