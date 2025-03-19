package com.example.my_canevas.controller;

import com.example.my_canevas.model.Service;
import com.example.my_canevas.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    // 🔹 GET - Récupérer tous les services
    @GetMapping
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return ResponseEntity.ok(services);
    }

    // 🔹 GET - Récupérer un service par ID
    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable String id) {
        Optional<Service> service = serviceRepository.findById(id);
        return service.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 POST - Ajouter un nouveau service
    @PostMapping
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        if (service.getId() != null && serviceRepository.existsById(service.getId())) {
            return ResponseEntity.badRequest().body(null); // 🔥 On refuse un POST avec un ID existant
        }
        
        if (service.getLabel() == null || service.getLabel().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
    
        Service savedService = serviceRepository.save(service);
        return ResponseEntity.status(201).body(savedService);
    }
    

    // 🔹 PUT - Modifier un service existant
    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable String id, @RequestBody Service service) {
        if (!serviceRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404 si le service n'existe pas
        }
        service.setId(id);
        Service updatedService = serviceRepository.save(service);
        return ResponseEntity.ok(updatedService); // 200 OK
    }

    // 🔹 DELETE - Supprimer un service par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable String id) {
        if (!serviceRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404 si le service n'existe pas
        }
        serviceRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content (succès sans contenu)
    }
}
