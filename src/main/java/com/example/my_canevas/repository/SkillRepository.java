// SkillRepository.java
package com.example.my_canevas.repository;

import com.example.my_canevas.model.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<Skill, String> {
}
