package com.example.my_canevas.controller;


import com.example.my_canevas.model.Message;
import com.example.my_canevas.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @Autowired
    private MessageRepository messageRepository;

    // Recevoir les messages du frontend et les envoyer à tous les clients
    @MessageMapping("/sendMessage")
    public void handleMessage(Message message) {
        messagingTemplate.convertAndSend("/topic/messages", message);
        messageRepository.save(message); // Sauvegarde dans MongoDB
    }
}
