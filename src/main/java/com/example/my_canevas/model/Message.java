package com.example.my_canevas.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class Message {

    private String visitorId;
    private String message;
    private String timestamp;

    // Constructeurs, getters et setters
    public Message(String visitorId, String message, String timestamp) {
        this.visitorId = visitorId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}