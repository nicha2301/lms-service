package com.nicha.content_service.repository;

import com.nicha.content_service.entity.Content;
import com.nicha.content_service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByCourseId(Long courseId);
}