package com.example.my_canevas.repository;

import com.example.my_canevas.model.Service;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<Service, String> {
}
