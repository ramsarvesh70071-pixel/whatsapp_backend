package com.rsm.rsmwhatsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF ko disable karein
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Sabhi API calls ko allow karein (No Token Required)
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable())); // H2 console ya frames ke liye

        return http.build();
    }
}