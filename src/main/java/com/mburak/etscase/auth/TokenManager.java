package com.mburak.etscase.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager implements Serializable {

    private static final long serialVersionUID = 7008375124389347049L;
    public static final long TOKEN_VALIDITY = 10 * 60 * 60;

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /*
    JWT Token will be generated with this method to authorize requests.
     */
    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(key).compact();
    }

    /*
    JWT Token will be validated with user and time. If
     */
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Claims claims = getClaims(token);
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }

    /*
    To get the user who owns the token.
     */
    public String getUsernameFromToken(String token) {
        final Claims claims = getClaims(token);
        return claims.getSubject();
    }

    /*
    To claim with token for the authorization header.
     */
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}