package com.myhomemadeshop.api.infrastructure.security;

import com.myhomemadeshop.api.user.domain.User;
import com.myhomemadeshop.api.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    Optional<User> userOptional = userRepository.findByEmail(s);
    if (!userOptional.isPresent()) {
      throw new UsernameNotFoundException(String.format("User with username %s, not found."));
    }
    return new CustomUserDetails(userOptional.get());
  }
}
