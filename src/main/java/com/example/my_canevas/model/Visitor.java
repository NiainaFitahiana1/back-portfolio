package com.example.my_canevas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "visitors")
public class Visitor {
    @Id
    private String id;
    private String ip;
    private String country;
    private String city;
    private String region;
    private double latitude;
    private double longitude;
    private String userAgent;
    private String language;
    private String screenResolution;
    private String referrer;
    private LocalDateTime timestamp;

    // ✅ Constructeur
    public Visitor(String ip, String country, String city, String region, double latitude, double longitude, String userAgent, String language, String screenResolution, String referrer) {
        this.ip = ip;
        this.country = country;
        this.city = city;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userAgent = userAgent;
        this.language = language;
        this.screenResolution = screenResolution;
        this.referrer = referrer;
        this.timestamp = LocalDateTime.now();
    }

    // ✅ Getters et Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
