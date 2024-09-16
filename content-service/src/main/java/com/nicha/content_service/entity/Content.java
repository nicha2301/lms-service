package com.nicha.content_service.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Document(collection = "contents")
public class Content {
    @Id
    private String id;
    private String title;
    private String description;
    private String url;
    private String fileType;
    private long fileSize;
}

