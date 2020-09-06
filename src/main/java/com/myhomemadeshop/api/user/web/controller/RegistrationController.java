package com.myhomemadeshop.api.user.web.controller;

import com.myhomemadeshop.api.user.UserService;
import com.myhomemadeshop.api.user.web.dtos.UserRequestDto;
import com.myhomemadeshop.api.user.web.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto dto) {

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.persist(dto));
  }

}
