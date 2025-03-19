package com.example.my_canevas.services;

import com.example.my_canevas.model.User;
import com.example.my_canevas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Fonction pour vérifier l'email et mot de passe
    public Optional<User> loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    // Nouvelle méthode pour récupérer un seul utilisateur
    public Optional<User> getSingleUser() {
        // Supposons qu'il n'y ait qu'un seul utilisateur dans la base de données
        Optional<User> user = userRepository.findAll().stream().findFirst();

        if (user.isPresent()) {
            return user;
        } else {
            // Si aucun utilisateur n'est trouvé
            throw new RuntimeException("Aucun utilisateur trouvé.");
        }
    }
}
