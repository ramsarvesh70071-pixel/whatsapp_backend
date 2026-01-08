package com.rsm.rsmwhatsapp.repository;

import com.rsm.rsmwhatsapp.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // @Query use karne se code saaf rehta hai aur error nahi aati
    @Query("SELECT m FROM ChatMessage m WHERE " +
            "(m.senderId = :u1 AND m.recipientId = :u2) OR " +
            "(m.senderId = :u2 AND m.recipientId = :u1) " +
            "ORDER BY m.timestamp ASC")
    List<ChatMessage> findChatHistory(@Param("u1") String u1, @Param("u2") String u2);
}