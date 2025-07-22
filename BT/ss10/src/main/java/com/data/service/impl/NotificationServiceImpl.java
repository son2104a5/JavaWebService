package com.data.service;

import com.data.model.entity.Notification;
import com.data.model.entity.Account;
import com.data.repo.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Account account, String content) {
        Notification noti = new Notification();
        noti.setAccount(account);
        noti.setContent(content);
        noti.setStatus("chưa đọc");
        noti.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(noti);
    }

    @Override
    public List<Notification> getNotificationsByAccountId(UUID accountId) {
        return notificationRepository.findByAccountId(accountId);
    }
}
