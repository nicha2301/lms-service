package com.nicha.content_service.service;

import com.nicha.content_service.entity.Content;
import com.nicha.content_service.repository.ContentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContentService {
    ContentRepository contentRepository;

    final String uploadDir = "/uploads";

    public Content uploadFile(MultipartFile file, Long courseId) throws IOException {
        Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Content content = new Content();
        content.setCourseId(courseId);
        content.setFileName(file.getOriginalFilename());
        content.setFileUrl(filePath.toString());
        content.setFileType(file.getContentType());
        content.setUploadTime(LocalDateTime.now());
        return contentRepository.save(content);
    }

    public Resource downloadFile(String fileName) throws MalformedURLException {
        Path filePath = Paths.get(uploadDir, fileName).toAbsolutePath().normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists()) {
            throw new RuntimeException("File not found " + fileName);
        }
        return resource;
    }
}
