package com.example.my_canevas.services;

import com.example.my_canevas.model.User;
import com.example.my_canevas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Chercher l'utilisateur par son email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

        // Retourner un objet UserDetails (ici, un CustomUserDetails)
        return new CustomUserDetails(user);
    }

    // Méthode pour récupérer un utilisateur par email
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);  // Retourne null si l'utilisateur n'est pas trouvé
    }

    // Méthode pour sauvegarder les modifications d'un utilisateur
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
