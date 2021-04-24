package com.kuroiryuu.mayas.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtUtil {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60L;
    private final Key key;

    public JwtUtil() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("aTAhBED7N0gesGBphZmQFVCb4ZLTOVR1U21/E449lGK6HDPCt8pGAJcI2apn0AfBL6qlB/QtQJhzj1qKkg5K3A=="));
    }

    public Optional<String> getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Optional<Date> getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> Optional<T> getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return Optional.ofNullable(claimsResolver.apply(claims));
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        Optional<Date> expirationDateFromToken = getExpirationDateFromToken(token);
        return expirationDateFromToken.isPresent() && expirationDateFromToken.get().before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key)
                .compact();
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }
}
