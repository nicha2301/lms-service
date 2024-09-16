package com.nicha.submission_service.controller;


import com.nicha.submission_service.entity.Submission;
import com.nicha.submission_service.entity.User;
import com.nicha.submission_service.service.SubmissionService;
import com.nicha.submission_service.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/submissions")
public class SubmissionController {

     SubmissionService submissionService;

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public Submission getSubmissionById(@PathVariable String id) {
        return submissionService.getSubmissionById(id);
    }

    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        return submissionService.createSubmission(submission);
    }

    @DeleteMapping("/{id}")
    public void deleteSubmission(@PathVariable String id) {
        submissionService.deleteSubmission(id);
    }
}