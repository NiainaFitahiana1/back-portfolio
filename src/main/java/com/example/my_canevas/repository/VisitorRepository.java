package com.example.my_canevas.repository;

import com.example.my_canevas.model.Visitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitorRepository extends MongoRepository<Visitor, String> {

    // 📌 Trouver les visiteurs enregistrés après une date donnée (nouveaux visiteurs)
    List<Visitor> findByTimestampAfter(LocalDateTime dateTime);
}
