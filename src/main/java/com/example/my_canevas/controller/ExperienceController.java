package com.example.my_canevas.controller;

import com.example.my_canevas.model.Experience;
import com.example.my_canevas.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    // Ajouter une expérience
    @PostMapping("/add")
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        return ResponseEntity.ok(experienceService.addExperience(experience));
    }

    // Récupérer toutes les expériences
    @GetMapping("/all")
    public ResponseEntity<List<Experience>> getExperiences() {
        return ResponseEntity.ok(experienceService.getAllExperiences());
    }

    // Récupérer une seule expérience par ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Experience>> getExperienceById(@PathVariable String id) {
        return ResponseEntity.ok(experienceService.getExperienceById(id));
    }

    // Mettre à jour une expérience
    @PutMapping("/update/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable String id, @RequestBody Experience experience) {
        return ResponseEntity.ok(experienceService.updateExperience(id, experience));
    }

    // Supprimer une expérience
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable String id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}
