package com.nicha.submission_service.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Document(collection = "submissions")
public class Submission {

    @Id
    private String id;
    private String studentId;
    private String assignmentId;
    private String contentUrl;
    private String submissionDate;
}

