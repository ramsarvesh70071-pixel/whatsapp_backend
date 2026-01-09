package com.rsm.rsmwhatsapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
public class FileStorageService {

    // TODO: Inhe apne Cloudinary Dashboard (API Keys) se replace karein
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "ddhr7ioqj",
            "api_key", "743745816737187",
            "api_secret", "w3ErT-gRQn32N0Q2ZXF8VYI2s4k"
    ));

    public String saveFile(MultipartFile file) throws IOException {
        // Direct Cloudinary par upload
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        // Uploaded image ka secure URL return karna
        return uploadResult.get("secure_url").toString();
    }
}