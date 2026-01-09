package com.rsm.rsmwhatsapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String userId;

    // Default Constructor
    public Contact() {}

    // All Arguments Constructor
    public Contact(Long id, String name, String phoneNumber, String userId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    // Getter aur Setter Methods (Inhe add karne se errors chale jayenge)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}