package com.nicha.content_service.controller;


import com.nicha.content_service.entity.Content;
import com.nicha.content_service.service.ContentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/content")
public class ContentController {
    ContentService contentService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadContent(@RequestParam("file") MultipartFile file,
                                           @RequestParam("courseId") Long courseId) throws IOException {
        Content content = contentService.uploadFile(file, courseId);
        return ResponseEntity.ok(content);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadContent(@PathVariable String fileName) throws IOException {
        Resource resource = contentService.downloadFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
