package com.data.service;

import com.data.model.entity.Account;
import com.data.model.entity.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    void sendNotification(Account account, String content);
    List<Notification> getNotificationsByAccountId(UUID accountId);
}
