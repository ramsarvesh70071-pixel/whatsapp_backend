package com.rsm.rsmwhatsapp.repository;

import com.rsm.rsmwhatsapp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // Ye line add karein:
    List<ChatMessage> findBySenderIdAndRecipientIdOrSenderIdAndRecipientIdOrderByTimestampAsc(
            String senderId, String recipientId, String senderId2, String recipientId2
    );
}