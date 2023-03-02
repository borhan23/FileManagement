package com.mburak.etscase.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManagerService {

    private static final int validity = 5 * 60 * 1000;
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /*
    To generate the token with token's attributes.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("skywalker")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    /*
    To validate the token expiration time and user.
     */
    public boolean tokenValidate(String token) {
        if (getUserFromToken(token) != null && isExpired(token)) {
            return true;
        }
        return false;
    }

    public String getUserFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

}
