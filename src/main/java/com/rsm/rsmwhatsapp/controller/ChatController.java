package com.rsm.rsmwhatsapp.controller;

import com.rsm.rsmwhatsapp.model.ChatMessage;
import com.rsm.rsmwhatsapp.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageRepository repository; // <-- Yahan naam 'repository' hai
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage message) {
        System.out.println("Message received in backend: " + message.getContent()); // Console mein check karein
        message.setTimestamp(LocalDateTime.now());

        repository.save(message); // Agar ye line chalegi toh pgAdmin mein data dikhega
        System.out.println("Message saved to database!");

        messagingTemplate.convertAndSendToUser(
                message.getRecipientId(),
                "/queue/messages",
                message
        );
    }
}