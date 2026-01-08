package com.rsm.rsmwhatsapp.repository;

import com.rsm.rsmwhatsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // String ki jagah Long (ID numeric hai)

    // 1. Online status ke hisaab se users dhoondna
    List<User> findAllByOnline(boolean online);

    // 2. FIXED: Phone number se search karne ke liye (Login/Register ke liye zaroori hai)
    User findByPhoneNumber(String phoneNumber);
}