package com.example.my_canevas.controller;

import com.example.my_canevas.model.Skill;
import com.example.my_canevas.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Skill> getSkillById(@PathVariable String id) {
        return skillRepository.findById(id);
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillRepository.save(skill);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable String id, @RequestBody Skill skill) {
        skill.setId(id);
        return skillRepository.save(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable String id) {
        skillRepository.deleteById(id);
    }
}
