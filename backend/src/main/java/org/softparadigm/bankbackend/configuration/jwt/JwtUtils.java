package org.softparadigm.bankbackend.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static org.softparadigm.bankbackend.configuration.jwt.SecurityConstants.AUTH_AUDIENCE;
import static org.softparadigm.bankbackend.configuration.jwt.SecurityConstants.EXPIRATION_TIME;

@Service
public class JwtUtils implements InitializingBean {

    @Value("${app.jwt.secret}") // throw exception if not found
    private String jwtSecret;

    private Key signKey;

    @Override
    public void afterPropertiesSet() {
        // Init key
        signKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(@NonNull String username) {
        Date expireDate = new Date(new Date().getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setAudience(AUTH_AUDIENCE)
                .setIssuer(AUTH_AUDIENCE)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(signKey)
                .compact();
    }

    public String parseUsername(@NonNull String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(signKey)
                .build()
                .parseClaimsJws(token);

        return claims.getBody().getSubject();
    }

}
