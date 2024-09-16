package com.nicha.content_service.controller;


import com.nicha.content_service.entity.Notification;
import com.nicha.content_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody Notification notification) {
        notificationService.sendNotification(notification);
    }

    @GetMapping("/{id}")
    public Notification getNotification(@PathVariable String id) {
        return notificationService.getNotification(id);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
    }
}
