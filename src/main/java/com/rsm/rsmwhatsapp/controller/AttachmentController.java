package com.rsm.rsmwhatsapp.controller;

import com.rsm.rsmwhatsapp.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/messages") // Flutter isi path par bhej raha hai
public class AttachmentController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Firebase par file save hogi aur URL milega
            String fileUrl = fileStorageService.saveFile(file);

            // Flutter code "fileUrl" key dhoond raha hai
            return ResponseEntity.ok(Map.of("fileUrl", fileUrl));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }
}