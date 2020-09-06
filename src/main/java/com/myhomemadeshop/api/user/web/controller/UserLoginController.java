package com.myhomemadeshop.api.user.web.controller;

import com.myhomemadeshop.api.infrastructure.security.jwt.JwtTokenService;
import com.myhomemadeshop.api.user.UserService;
import com.myhomemadeshop.api.user.domain.User;
import com.myhomemadeshop.api.user.web.dtos.LoginRequestDto;
import com.myhomemadeshop.api.user.web.dtos.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class UserLoginController {

  private final UserService userService;
  private final JwtTokenService tokenService;
  private final AuthenticationManager authenticationManager;

  @PostMapping
  public ResponseEntity<LoginResponseDto> authenticate(
      @Valid @RequestBody LoginRequestDto request) {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    } catch (BadCredentialsException e) {
      System.out.println(e.getLocalizedMessage());
      throw new EntityNotFoundException("Bad credentials");
    }

    User user = userService.findEntityByEmail(request.getEmail());

    return ResponseEntity.ok(new LoginResponseDto(tokenService.createToken(user)));
  }
}
