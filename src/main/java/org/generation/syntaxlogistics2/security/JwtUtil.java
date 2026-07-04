package org.generation.syntaxlogistics2.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Llave secreta para firmar el token (en un proyecto real va en application.properties, no aquí)
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 horas

    // Genera un token nuevo para un email dado (login exitoso)
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    // Saca el email guardado dentro del token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    // Verifica que el token sea válido y no haya expirado
    public boolean isTokenValid(String token, String email) {
        String extractedEmail = extractEmail(token);
        Date expiration = extractClaim(token, Claims::getExpiration);
        return extractedEmail.equals(email) && expiration.after(new Date());
    }
}