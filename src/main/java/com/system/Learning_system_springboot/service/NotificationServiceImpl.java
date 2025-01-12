package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.entity.AppAnnouncement;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.repo.AppAnnouncementRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{
    private final AppAnnouncementRepository appAnnouncementRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationServiceImpl(AppAnnouncementRepository appAnnouncementRepository, SimpMessagingTemplate messagingTemplate) {
        this.appAnnouncementRepository = appAnnouncementRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    @Scheduled(fixedRate = 60000) // Check every minute
    public void checkScheduledAnnouncements() {
        List<AppAnnouncement> announcements = appAnnouncementRepository.findByStatusAndScheduledAtBefore(Status.SCHEDULED, LocalDateTime.now());
        for (AppAnnouncement announcement : announcements) {
            sendNotification(announcement);
            announcement.setStatus(Status.PUBLISHED);
            appAnnouncementRepository.save(announcement);
        }
    }
    @Override
    public void sendNotification(AppAnnouncement announcement) {
        messagingTemplate.convertAndSend("/topic/notifications", announcement);
    }
}