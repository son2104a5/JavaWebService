package com.data.service;

import com.data.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Long userId, Feedback feedback);

    List<Feedback> getAllFeedbacks();

    Feedback replyToFeedback(Long feedbackId, String replyContent);
}
