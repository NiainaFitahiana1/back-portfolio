package com.example.my_canevas.controller;

import com.example.my_canevas.model.User;
import com.example.my_canevas.model.Skill;
import com.example.my_canevas.model.Service;
import com.example.my_canevas.model.Experience;
import com.example.my_canevas.services.UserService;
import com.example.my_canevas.services.SkillService;
import com.example.my_canevas.services.ServiceService;
import com.example.my_canevas.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

@CrossOrigin(origins = "http://localhost:5173")
public class ShowMeController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private SkillService skillService;
    
    @Autowired
    private ServiceService serviceService;
    
    @Autowired
    private ExperienceService experienceService;

    @GetMapping("/show/user")
    public User getSingleUser() {
        Optional<User> user = userService.getSingleUser();
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("Aucun utilisateur trouvé.");
        }
    }

    // Méthode pour afficher les compétences
    @GetMapping("/show/skills")
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();  // Retourne une liste de toutes les compétences
    }

    // Méthode pour afficher les services
    @GetMapping("/show/services")
    public List<Service> getAllServices() {
        return serviceService.getAllServices();  // Retourne une liste de tous les services
    }

    // Méthode pour afficher les expériences
    @GetMapping("/show/experiences")
    public List<Experience> getAllExperiences() {
        return experienceService.getAllExperiences();  // Retourne une liste de toutes les expériences
    }
}
