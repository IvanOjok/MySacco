package com.jambo.mysacco.service;


import com.jambo.mysacco.models.User;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


import java.util.Date;

@Service
public class JWTService {

    private static final String SECRET = "MY_SECRET_KEY";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(Long.toString(user.getUserId()))
                .claim("name", user.getUserName())
                .claim("sacco", user.getSaccoId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }
}

