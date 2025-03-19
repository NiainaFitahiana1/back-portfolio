package com.example.my_canevas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileController {

    // Chemin absolu vers le dossier uploads
    private static final String UPLOAD_DIR = "uploads"; // Relatif au niveau de pom.xml

    // Méthode pour uploader un fichier
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // Créer le répertoire si nécessaire
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return ResponseEntity.status(500).body("Impossible de créer le répertoire uploads.");
            }
        }

        // Nettoyer le nom du fichier (retirer les caractères spéciaux)
        String cleanFileName = file.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

        // Créer le fichier final à l'endroit de destination
        Path filePath = Paths.get(UPLOAD_DIR, cleanFileName);
        try {
            // Sauvegarder le fichier sur le serveur
            file.transferTo(filePath.toFile());
            return ResponseEntity.ok("Fichier téléchargé avec succès : " + cleanFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors du téléchargement du fichier.");
        }
    }

    // Méthode pour accéder aux fichiers téléchargés (exemple)
    @GetMapping("/files/{filename}")
    public ResponseEntity<byte[]> getFile(@PathVariable String filename) {
        // Charger le fichier depuis le répertoire uploads
        Path filePath = Paths.get(UPLOAD_DIR, filename);
        try {
            byte[] fileContent = Files.readAllBytes(filePath);
            return ResponseEntity.ok().body(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        }
    }
}
