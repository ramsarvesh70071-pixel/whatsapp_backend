package com.rsm.rsmwhatsapp.repository;

import com.rsm.rsmwhatsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByOnline(boolean online);
    // Phone number se search karne ke liye defaultfindById() kaafi hai
}