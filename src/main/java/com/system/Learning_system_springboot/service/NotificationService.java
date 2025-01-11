package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    // Simulate sending a notification every 10 seconds
//    @Scheduled(fixedRate = 10000)
//    public void sendPeriodicNotifications() {
//        String message = "This is a periodic notification!";
//        String timestamp = LocalDateTime.now().toString();
//        Notification notification = new Notification(message, timestamp);
//
//        // Send the notification to all subscribers of "/topic/notifications"
//        messagingTemplate.convertAndSend("/topic/notifications", notification);
//
//        System.out.println("Sent notification: " + message);
//    }
}