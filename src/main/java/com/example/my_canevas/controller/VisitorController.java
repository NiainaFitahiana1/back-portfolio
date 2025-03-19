package com.example.my_canevas.controller;

import com.example.my_canevas.model.Visitor;
import com.example.my_canevas.repository.VisitorRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visitors")
@CrossOrigin(origins = "http://localhost:5173") 
public class VisitorController {

    @Autowired
    private VisitorRepository visitorRepository;

    @PostMapping
    public Visitor saveVisitor(@RequestBody Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    // 📌 Récupérer le nombre total de visiteurs
    @GetMapping("/total")
    public long getTotalVisitors() {
        return visitorRepository.count();
    }

    // 📌 Récupérer le nombre de nouveaux visiteurs (dernière semaine)
    @GetMapping("/new")
    public long getNewVisitors() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        return visitorRepository.findAll().stream()
                .filter(visitor -> visitor.getTimestamp().isAfter(oneWeekAgo))
                .count();
    }

    @GetMapping("/weekly")
    public List<Map<String, Object>> getVisitorsByWeek() {
        LocalDateTime now = LocalDateTime.now();
        
        // Regroupe les visiteurs par semaine
        return visitorRepository.findAll().stream()
                .collect(Collectors.groupingBy(visitor -> visitor.getTimestamp()
                        .truncatedTo(ChronoUnit.WEEKS))) // Grouper par semaine
                .entrySet().stream()
                .map(entry -> {
                    // Créer une HashMap au lieu de Map.of pour éviter l'erreur
                    Map<String, Object> weekData = new HashMap<>();
                    weekData.put("week", entry.getKey().toString());  // Semaine
                    weekData.put("visitors", entry.getValue().size()); // Nombre de visiteurs
                    return weekData;
                })
                .collect(Collectors.toList());
    }
}
