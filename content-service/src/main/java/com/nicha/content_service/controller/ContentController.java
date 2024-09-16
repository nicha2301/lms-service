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
import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/contents")
public class ContentController {
    ContentService contentService;

    @GetMapping
    public List<Content> getAllContents() {
        return contentService.getAllContents();
    }

    @GetMapping("/{id}")
    public Content getContentById(@PathVariable String id) {
        return contentService.getContentById(id);
    }

    @PostMapping
    public Content createContent(@RequestBody Content content) {
        return contentService.createContent(content);
    }

    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable String id) {
        contentService.deleteContent(id);
    }
}