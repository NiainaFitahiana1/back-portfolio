package com.example.my_canevas.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "DsEk7cRZclv5ak+TaEnYkEwMn+ythUSh1XYBhEhZBbw="; // Change cette clé en production

    // Générer un token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // Ajouter le nom d'utilisateur comme sujet
                .setIssuedAt(new Date())  // Date de création
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // Expire dans 1 heure
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Signer le token avec la clé secrète
                .compact();
    }

    // Valider un token JWT
    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);  // Vérifier la signature du token
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extraire le nom d'utilisateur du token JWT
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
