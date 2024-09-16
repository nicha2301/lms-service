package com.nicha.content_service.repository;

import com.nicha.content_service.entity.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContentRepository extends MongoRepository<Content, String> {

    List<Content> findByCourseId(Long courseId);
}