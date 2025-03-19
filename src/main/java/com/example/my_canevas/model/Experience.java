package com.example.my_canevas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "experiences")
public class Experience {

    @Id
    private String id;
    private String titre;
    private String date;
    private String entreprise;
    private String type;
    private List<String> technos; // Utilisation d'une liste pour stocker les technologies
    private String description;
    private String lienGit;
    private String lien1;

    // Getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getEntreprise() { return entreprise; }
    public void setEntreprise(String entreprise) { this.entreprise = entreprise; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public List<String> getTechnos() { return technos; }
    public void setTechnos(List<String> technos) { this.technos = technos; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getLienGit() { return lienGit; }
    public void setLienGit(String lienGit) { this.lienGit = lienGit; }
    
    public String getLien1() { return lien1; }
    public void setLien1(String lien1) { this.lien1 = lien1; }
}
