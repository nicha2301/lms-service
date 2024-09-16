package com.nicha.submission_service.repository;

import com.nicha.submission_service.entity.Submission;
import com.nicha.submission_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends MongoRepository<Submission, String> {
}
