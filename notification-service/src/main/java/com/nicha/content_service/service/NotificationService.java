package com.nicha.content_service.service;

import com.nicha.content_service.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
public class NotificationService {

    RedisTemplate<String, Notification> redisTemplate;

    private static final String NOTIFICATION_PREFIX = "NOTIFICATION_";

    public void sendNotification(Notification notification) {
        String notificationId = NOTIFICATION_PREFIX + UUID.randomUUID();
        redisTemplate.opsForValue().set(notificationId, notification, Duration.ofMinutes(30));  // TTL là 30 phút
    }

    public Notification getNotification(String id) {
        return redisTemplate.opsForValue().get(NOTIFICATION_PREFIX + id);
    }

    public void deleteNotification(String id) {
        redisTemplate.delete(NOTIFICATION_PREFIX + id);
    }
}
