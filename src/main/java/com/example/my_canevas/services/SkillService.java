package com.example.my_canevas.services;

import com.example.my_canevas.model.Skill;
import com.example.my_canevas.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // Récupérer toutes les compétences
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    // Récupérer une compétence par son ID
    public Optional<Skill> getSkillById(String id) {
        return skillRepository.findById(id);
    }

    // Ajouter une nouvelle compétence
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // Mettre à jour une compétence existante
    public Skill updateSkill(String id, Skill skillDetails) {
        if (skillRepository.existsById(id)) {
            skillDetails.setId(id);
            return skillRepository.save(skillDetails);
        } else {
            return null;
        }
    }

    // Supprimer une compétence par son ID
    public void deleteSkill(String id) {
        skillRepository.deleteById(id);
    }
}
