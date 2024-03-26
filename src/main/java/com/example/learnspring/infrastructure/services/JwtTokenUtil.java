package com.example.learnspring.infrastructure.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import java.io.Serializable;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.signing.key}")
    public String SIGNING_KEY;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getPasswordFromToken(String token) {
        return getClaimFromToken(token, Claims::getAudience);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public Date getIssueAt(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isIssueAt(String token) {
        final Date issueAt = getIssueAt(token);
        return issueAt.after(new Date());
    }

    public Boolean validateToken(String token) {
        return (!isIssueAt(token));
    }

}
