package com.example.my_canevas.services;

import com.example.my_canevas.model.Service;
import com.example.my_canevas.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    // Récupérer tous les services
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    // Récupérer un service par son ID
    public Optional<Service> getServiceById(String id) {
        return serviceRepository.findById(id);
    }

    // Ajouter un nouveau service
    public Service addService(Service service) {
        return serviceRepository.save(service);
    }

    // Mettre à jour un service
    public Service updateService(String id, Service serviceDetails) {
        if (serviceRepository.existsById(id)) {
            serviceDetails.setId(id);
            return serviceRepository.save(serviceDetails);
        } else {
            return null;
        }
    }

    // Supprimer un service par ID
    public void deleteService(String id) {
        serviceRepository.deleteById(id);
    }
}
