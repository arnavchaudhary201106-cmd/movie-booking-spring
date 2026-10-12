package com.example.practice.service;

import com.example.practice.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String generateToken(String username){
   long now =System.currentTimeMillis();
   long expirationTime=now+3600*1000;
   return Jwts.builder().subject(username).issuedAt(new  Date(now)).expiration(new Date(expirationTime)).signWith(key).compact();
    }
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }
    public boolean validate(String token , UserDetails userDetails){
 String username = extractUsername(token);
 return username.equals(userDetails.getUsername());
    }

}
