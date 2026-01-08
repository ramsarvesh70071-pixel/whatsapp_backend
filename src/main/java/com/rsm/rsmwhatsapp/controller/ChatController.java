package com.rsm.rsmwhatsapp.controller;

import com.rsm.rsmwhatsapp.model.ChatMessage;
import com.rsm.rsmwhatsapp.model.User;
import com.rsm.rsmwhatsapp.repository.ChatMessageRepository;
import com.rsm.rsmwhatsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageRepository repository;

    @Autowired
    private UserRepository userRepository;

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

    // --- 2. Chat History API (Updated to use findChatHistory) ---
    @GetMapping("/api/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> getChatHistory(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        // Humne repository mein jo naya @Query banaya tha, use yahan call kar rahe hain
        List<ChatMessage> history = repository.findChatHistory(senderId, recipientId);

        return ResponseEntity.ok(history);
    }

    // --- 3. All Users List API ---
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}