package com.rsm.rsmwhatsapp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            // 1. Render ke Environment Variable se JSON string uthayye
            String firebaseConfigJson = System.getenv("FIREBASE_JSON_DATA");
            InputStream serviceAccount;

            if (firebaseConfigJson != null && !firebaseConfigJson.isEmpty()) {
                // Render/Production mode: Variable se data read karein
                serviceAccount = new ByteArrayInputStream(firebaseConfigJson.getBytes(StandardCharsets.UTF_8));
                System.out.println("✅ Initializing Firebase using Environment Variable...");
            } else {
                // Local mode: Agar variable nahi hai toh purani file dhoondhein
                serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-service-account.json");
                System.out.println("🏠 Initializing Firebase using local JSON file...");
            }

            if (serviceAccount == null) {
                throw new IOException("Firebase config data (JSON file or Environment Variable) nahi mila!");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("🔥 Firebase Initialized Successfully!");
            }
        } catch (IOException e) {
            System.out.println("❌ Firebase Init Error: " + e.getMessage());
        }
    }
}