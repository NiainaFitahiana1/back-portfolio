package com.example.my_canevas.controller;

import com.example.my_canevas.config.JwtUtil;
import com.example.my_canevas.model.PasswordChangeRequest;
import com.example.my_canevas.services.CustomUserDetailsService;
import com.example.my_canevas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.my_canevas.model.JwtResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import java.util.Set;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired PasswordEncoder passwordEncoder;
    // Stockage temporaire des tokens invalidés (utiliser Redis ou une base de données en production)
    private Set<String> invalidatedTokens = new HashSet<>();

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            String token = jwtUtil.generateToken(authentication.getName());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de l'authentification");
        }
    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token invalide");
        }
        
        String jwt = token.substring(7); // Retirer "Bearer "
        invalidatedTokens.add(jwt);
        
        return ResponseEntity.ok("Déconnexion réussie !");
    }

    @GetMapping("/api/auth/user")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur non authentifié");
        }
        return ResponseEntity.ok(authentication.getPrincipal());
    }

    @PutMapping("/api/auth/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur non authentifié");
        }
        
        User existingUser = userDetailsService.findByEmail(authentication.getName());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }
        
        // Mettre à jour les informations de l'utilisateur
        existingUser.setNom(updatedUser.getNom());
        existingUser.setPrenom(updatedUser.getPrenom());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setTelephone(updatedUser.getTelephone());
        existingUser.setProfil(updatedUser.getProfil());
        existingUser.setAdresse(updatedUser.getAdresse());
        existingUser.setLinkPhoto(updatedUser.getLinkPhoto());
        existingUser.setLinkedin(updatedUser.getLinkedin());
        existingUser.setGit(updatedUser.getGit());
        existingUser.setBio(updatedUser.getBio());

        userDetailsService.saveUser(existingUser);
        return ResponseEntity.ok("Mise à jour réussie !");
    }

    @PutMapping("/api/auth/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequest request, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur non authentifié");
        }

        User existingUser = userDetailsService.findByEmail(authentication.getName());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }

        // Vérifier que le mot de passe actuel correspond
        if (!passwordEncoder.matches(request.getOldPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mot de passe actuel incorrect");
        }

        // Valider les nouveaux mots de passe
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Les mots de passe ne correspondent pas");
        }

        // Hacher le nouveau mot de passe
        String hashedNewPassword = passwordEncoder.encode(request.getNewPassword());

        // Mettre à jour le mot de passe dans l'utilisateur
        existingUser.setPassword(hashedNewPassword);
        userDetailsService.saveUser(existingUser);

        return ResponseEntity.ok("Mot de passe changé avec succès");
    }

}
