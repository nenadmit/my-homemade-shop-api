package com.myhomemadeshop.api.infrastructure.security.jwt;

import com.myhomemadeshop.api.infrastructure.exception.JwtValidationException;
import com.myhomemadeshop.api.infrastructure.security.config.SecurityConstants;
import com.myhomemadeshop.api.user.domain.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.myhomemadeshop.api.infrastructure.security.config.SecurityConstants.*;

@Service
public class JwtTokenService {


    public String createToken(User user) {

        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getEmail());
        claims.put("sub",user.getEmail());

        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(EXPIRATION_TIME_IN_MINUTES, ChronoUnit.MINUTES);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getFirstName())
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public void validateToken(String token) {
        try {
            parseToken(token);
        } catch (MalformedJwtException e) {
            throw new JwtValidationException("Token signature invalid or malformed");
        }catch (ExpiredJwtException e){
            throw new JwtValidationException("Token has expired");
        }
    }

    public boolean isTokenNonExpired(String token) {
        return !getExpirationDate(token).before(new Date());
    }

    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public String getUsername(String token) {
        return (String) parseToken(token).get("username");
    }

    private Date getExpirationDate(String token) {
        return parseToken(token).getExpiration();
    }
}
