package com.myhomemadeshop.api.infrastructure.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhomemadeshop.api.infrastructure.exception.pojo.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JwtErrorHandler {

    private ObjectMapper mapper;

    @Autowired
    public JwtErrorHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void sendErrorResponse(RuntimeException ex, HttpServletResponse response)
            throws IOException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ApiError responseEntity =
                new ApiError(
                        ex.getMessage(),HttpStatus.UNAUTHORIZED);

        String jsonResponse = "";
        try {
            jsonResponse = mapper.writeValueAsString(responseEntity);
        } catch (JsonProcessingException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,e.getMessage());
        }

        response.getWriter().write(jsonResponse);
    }

}
