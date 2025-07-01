package com.example.my_canevas.controller;

import com.example.my_canevas.model.Visitor;
import com.example.my_canevas.repository.VisitorRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
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
                try {
                    List<Visitor> visitors = visitorRepository.findAll();
                    if (visitors.isEmpty()) {
                        throw new IllegalStateException("Aucun visiteur trouvé dans la base de données.");
                    }

                    WeekFields weekFields = WeekFields.of(Locale.getDefault());

                    // Regrouper les visiteurs par année + numéro de semaine
                    return visitors.stream()
                            .collect(Collectors.groupingBy(visitor -> {
                                LocalDateTime timestamp = visitor.getTimestamp();
                                int weekNumber = timestamp.get(weekFields.weekOfWeekBasedYear());
                                int year = timestamp.getYear();
                                return year + "-W" + weekNumber;
                            }))
                            .entrySet().stream()
                            .map(entry -> {
                                Map<String, Object> weekData = new HashMap<>();
                                weekData.put("week", entry.getKey());  // Exemple : "2025-W17"
                                weekData.put("visitors", entry.getValue().size());
                                return weekData;
                            })
                            .sorted(Comparator.comparing(map -> (String) map.get("week")))
                            .collect(Collectors.toList());

                } catch (Exception e) {
                    System.err.println("Erreur lors de la récupération des visiteurs par semaine : " + e.getMessage());
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur serveur: " + e.getMessage(), e);
                }
            }

}
