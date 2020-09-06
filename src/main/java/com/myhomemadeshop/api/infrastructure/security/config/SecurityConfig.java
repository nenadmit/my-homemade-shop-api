package com.myhomemadeshop.api.infrastructure.security.config;

import com.myhomemadeshop.api.infrastructure.security.MyUserDetailsService;
import com.myhomemadeshop.api.infrastructure.security.filter.CorsFilter;
import com.myhomemadeshop.api.infrastructure.security.filter.JwtAuthenticationFilter;
import com.myhomemadeshop.api.infrastructure.security.filter.JwtValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final MyUserDetailsService myUserDetailsService;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtValidationFilter jwtValidationFilter;
  private final CorsFilter corsFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/**").authorizeRequests().anyRequest().permitAll();

    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf()
        .disable();

    http.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(jwtValidationFilter, JwtAuthenticationFilter.class);
    http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
