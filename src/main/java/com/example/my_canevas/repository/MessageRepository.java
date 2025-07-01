package com.example.my_canevas.repository;

import com.example.my_canevas.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
    // Possibilité d'ajouter des requêtes personnalisées si nécessaire
}
