package com.rsm.rsmwhatsapp.repository;

import com.rsm.rsmwhatsapp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    // Do users ke beech ki chat history nikalne ke liye
    List<ChatMessage> findBySenderIdAndRecipientId(String senderId, String recipientId);
}