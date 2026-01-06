package com.rsm.rsmwhatsapp.controller;

import com.rsm.rsmwhatsapp.model.ChatMessage;
import com.rsm.rsmwhatsapp.model.User; // User model import karein
import com.rsm.rsmwhatsapp.repository.ChatMessageRepository;
import com.rsm.rsmwhatsapp.repository.UserRepository; // UserRepository import karein
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Ye line add karein taaki loading khatam ho sake
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageRepository repository;

    @Autowired
    private UserRepository userRepository; // UserRepository inject ho gaya

    // --- 1. Real-time Messaging (WebSocket) ---
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage message) {
        System.out.println("Message received: " + message.getContent());
        message.setTimestamp(LocalDateTime.now());
        repository.save(message);

        messagingTemplate.convertAndSendToUser(
                message.getRecipientId(),
                "/queue/messages",
                message
        );
    }

    // --- 2. Chat History API ---
    @GetMapping("/api/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> getChatHistory(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        List<ChatMessage> history = repository.findBySenderIdAndRecipientIdOrSenderIdAndRecipientIdOrderByTimestampAsc(
                senderId, recipientId, recipientId, senderId);

        return ResponseEntity.ok(history);
    }

    // --- 3. All Users List API (Naya feature) ---
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}