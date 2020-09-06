package com.myhomemadeshop.api.infrastructure.security.filter;

import com.myhomemadeshop.api.infrastructure.exception.JwtValidationException;
import com.myhomemadeshop.api.infrastructure.security.JwtErrorHandler;
import com.myhomemadeshop.api.infrastructure.security.config.SecurityConstants;
import com.myhomemadeshop.api.infrastructure.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {

  private final JwtErrorHandler errorHandler;
  private final JwtTokenService jwtUtility;

  /**
   * Checks if the authorization is present and contains a token with Bearer prefix if token is
   * present, check whether it can be validated.
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {

    String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      errorHandler.sendErrorResponse(
          new JwtValidationException("Bearer token not present."), httpServletResponse);
      return;
    }

    String token = header.substring(7);
    try {
      jwtUtility.validateToken(token);
      jwtUtility.isTokenNonExpired(token);
    } catch (JwtValidationException e) {
      errorHandler.sendErrorResponse(e, httpServletResponse);
      return;
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {

    AntPathMatcher pathMatcher = new AntPathMatcher();
    return Arrays.stream(SecurityConstants.getWhitelistedUrl())
        .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
  }
}
