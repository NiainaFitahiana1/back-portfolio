package com.example.my_canevas.config;

import com.example.my_canevas.model.User;
import com.example.my_canevas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Vérifier si l'utilisateur existe déjà
            if (userRepository.findByEmail("manohitiana@gmail.com").isEmpty()) {
                User user = new User();
                user.setNom("Manohitiana");
                user.setPrenom("Fitahiana");
                user.setProfil("Développeur Web");
                user.setBio("Passionné par le développement web et les nouvelles technologies.");
                user.setEmail("manohitiana@gmail.com");
                user.setTelephone("+261 34 00 123 45");
                user.setLinkedin("https://www.linkedin.com/in/manohitiana-fitahiana");
                user.setGit("https://github.com/manohitiana");
                user.setLinkPhoto("https://example.com/photo.jpg");
                user.setAdresse("Antananarivo, Madagascar");
                
                // Encodage du mot de passe
                user.setPassword(passwordEncoder.encode("123456"));

                userRepository.save(user);
                System.out.println("Utilisateur initial inséré dans la base de données !");
            } else {
                System.out.println("Utilisateur déjà existant !");
            }
        };
    }
}
