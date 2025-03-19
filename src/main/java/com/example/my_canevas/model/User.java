package com.example.my_canevas.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private String profil;
    private String bio;
    private String email;
    private String telephone;
    private String linkedin;
    private String git;
    private String linkPhoto;
    private String password;
    private String adresse;

    // Getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getProfil() { return profil; }
    public void setProfil(String profil) { this.profil = profil; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
    public String getGit() { return git; }
    public void setGit(String git) { this.git = git; }
    public String getLinkPhoto() { return linkPhoto; }
    public void setLinkPhoto(String linkPhoto) { this.linkPhoto = linkPhoto; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
}
