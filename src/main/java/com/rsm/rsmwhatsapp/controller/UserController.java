package com.rsm.rsmwhatsapp.controller;

import com.rsm.rsmwhatsapp.model.User;
import com.rsm.rsmwhatsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 1. User Register/Login (Sahi hai)
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // Pehle check karein agar user pehle se exist karta hai toh wahi return karein
        User existingUser = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (existingUser != null) {
            existingUser.setOnline(true);
            return userRepository.save(existingUser);
        }
        user.setOnline(true);
        return userRepository.save(user);
    }

    // 2. FIXED: Saare users ki list (Ye miss ho gaya tha)
    // Flutter ApiService isi ko call kar raha hai
    @GetMapping("")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 3. Online users ki list
    @GetMapping("/online")
    public List<User> getOnlineUsers() {
        return userRepository.findAllByOnline(true);
    }
}