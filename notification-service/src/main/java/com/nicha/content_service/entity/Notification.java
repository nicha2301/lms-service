package com.nicha.content_service.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Notification implements Serializable {
    private String id;
    private String message;
    private String recipient;
    private String createdAt;

}

