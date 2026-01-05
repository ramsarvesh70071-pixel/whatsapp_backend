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

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setOnline(true);
        return userRepository.save(user);
    }

    @GetMapping("/online")
    public List<User> getOnlineUsers() {
        return userRepository.findAllByOnline(true);
    }
}