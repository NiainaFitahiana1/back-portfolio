package com.example.my_canevas.services;

import com.example.my_canevas.model.Experience;
import com.example.my_canevas.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;
    public Experience addExperience(Experience experience) {
        experience.setId(null); 
        return experienceRepository.save(experience);
    }
    public Experience updateExperience(String id, Experience experience) {
        Optional<Experience> existingExperience = experienceRepository.findById(id);
        
        if (existingExperience.isPresent()) {
            Experience updatedExperience = existingExperience.get();
            updatedExperience.setTitre(experience.getTitre());
            updatedExperience.setDate(experience.getDate());
            updatedExperience.setEntreprise(experience.getEntreprise());
            updatedExperience.setType(experience.getType());
            updatedExperience.setTechnos(experience.getTechnos());
            updatedExperience.setDescription(experience.getDescription());
            updatedExperience.setLienGit(experience.getLienGit());
            updatedExperience.setLien1(experience.getLien1());

            return experienceRepository.save(updatedExperience);
        } else {
            throw new RuntimeException("Expérience avec l'ID " + id + " non trouvée.");
        }
    }

    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    public Optional<Experience> getExperienceById(String id) {
        return experienceRepository.findById(id);
    }

    public void deleteExperience(String id) {
        experienceRepository.deleteById(id);
    }
}
