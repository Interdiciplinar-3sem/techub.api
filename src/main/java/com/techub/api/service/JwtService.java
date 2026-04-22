package com.techub.api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey; // Keys.secretKeyFor(SignatureAlgorithm.HS256)

    // decode byte -> key (chave salva em bytes no properties)
    private Key getSignUpKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Value("${security.jwt-expiration-time}")
    private long jwtExpiration; // 60 * 60

    private String generateToken(String email){
        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +  jwtExpiration))
                .signWith(getSignUpKey())
                .compact();
    }

    // Aviso de metodo deprecated
    private String extractEmail(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignUpKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Boolean isTokenValid(String token, UserDetails userDetails){
        try {
            String userName = extractEmail(token);
            return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private Date getExpirationTime(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignUpKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }


    public Boolean isTokenExpired(String token){
        Date expiration = getExpirationTime(token);
        return expiration.before(new Date());
    }
}
