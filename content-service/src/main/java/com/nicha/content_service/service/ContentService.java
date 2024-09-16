package com.nicha.content_service.service;

import com.nicha.content_service.entity.Content;
import com.nicha.content_service.repository.ContentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContentService {

    ContentRepository contentRepository;

    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    public Content getContentById(String id) {
        return contentRepository.findById(id).orElse(null);
    }

    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    public void deleteContent(String id) {
        contentRepository.deleteById(id);
    }
}
