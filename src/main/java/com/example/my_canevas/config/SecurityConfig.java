package com.example.my_canevas.config;

import com.example.my_canevas.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService customUserDetailsService;

    // Constructor pour injecter le jwtFilter et le customUserDetailsService
    public SecurityConfig(JwtFilter jwtFilter, CustomUserDetailsService customUserDetailsService) {
        this.jwtFilter = jwtFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    // Configuration du SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Activation de CORS
            .csrf(csrf -> csrf.disable())  // Désactiver CSRF pour une API REST (habituellement désactivé pour les API)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Mode stateless (pas de session)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login", "/show/*","/api/files/upload/*").permitAll()  // Routes publiques sans authentification
                .requestMatchers("/api/auth/user", "/api/services/**", "/api/experiences/**", 
                                  "/api/users/**", "/api/skills/**", "/api/visitors/**").authenticated()  // Routes nécessitant une authentification
                .anyRequest().permitAll()  // Les autres routes sont accessibles sans authentification
            )
            .exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint((request, response, authException) -> {  // Gestion des erreurs d'authentification
                    System.out.println("🚫 Accès refusé : " + authException.getMessage());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous devez être connecté.");
                })
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);  // Ajout du filtre JWT avant le filtre par défaut

        return http.build();  // Construction du filtre de sécurité
    }

    // Bean pour le PasswordEncoder (bcrypt utilisé ici)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Utilisation de bcrypt pour le hachage des mots de passe
    }

    // Bean pour l'AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configuration de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));  // Autorisation de l'origine de l'application React en développement
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));  // Méthodes HTTP autorisées
        configuration.setAllowedHeaders(List.of("*"));  // Autorisation de tous les headers
        configuration.setAllowCredentials(true);  // Autorisation des cookies et autres informations d'authentification

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Configuration CORS pour toutes les routes
        return source;
    }
}
