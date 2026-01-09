package com.rsm.rsmwhatsapp.controller;

import com.rsm.rsmwhatsapp.model.Contact;
import com.rsm.rsmwhatsapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/{userId}")
    public List<Contact> getContacts(@PathVariable String userId) {
        return contactRepository.findByUserId(userId);
    }

    @PostMapping("/add")
    public Contact addContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @PutMapping("/edit/{id}")
    public Contact update(@PathVariable Long id, @RequestBody Contact details) {
        Contact c = contactRepository.findById(id).orElseThrow();
        c.setName(details.getName());
        c.setPhoneNumber(details.getPhoneNumber());
        return contactRepository.save(c);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }
}