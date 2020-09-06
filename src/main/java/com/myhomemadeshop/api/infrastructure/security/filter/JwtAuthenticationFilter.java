package com.myhomemadeshop.api.infrastructure.security.filter;

import com.myhomemadeshop.api.infrastructure.security.JwtErrorHandler;
import com.myhomemadeshop.api.infrastructure.security.MyUserDetailsService;
import com.myhomemadeshop.api.infrastructure.security.config.SecurityConstants;
import com.myhomemadeshop.api.infrastructure.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final MyUserDetailsService userDetailsService;
    private final JwtTokenService jwtUtility;
    private final JwtErrorHandler errorHandler;


    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain)
            throws ServletException, IOException {

        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
        String username = jwtUtility.getUsername(token);
        UserDetails userDetail;
        try{
            userDetail = userDetailsService.loadUserByUsername(username);
        }catch (UsernameNotFoundException e){
            errorHandler.sendErrorResponse(
                    e, httpServletResponse);
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        AntPathMatcher pathMatcher = new AntPathMatcher();
        return Arrays.stream(SecurityConstants.getWhitelistedUrl())
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

}
