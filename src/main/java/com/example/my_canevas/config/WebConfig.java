package com.example.my_canevas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Permet l'accès à toutes les routes API
                .allowedOrigins("http://localhost:5173") // Le frontend qui fait les requêtes
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP autorisées
                .allowedHeaders("*") // Tous les headers sont autorisés
                .allowCredentials(true); // Permet l'envoi de cookies
    }
}

