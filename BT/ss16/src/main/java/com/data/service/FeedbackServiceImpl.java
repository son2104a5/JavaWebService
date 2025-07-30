package com.data.service;

import com.data.model.entity.Feedback;
import com.data.repository.FeedbackRepository;
import com.data.repository.TicketOrderRepository;
import com.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepo;
    private final TicketOrderRepository ticketOrderRepo;
    private final UserRepository userRepo;

    @Override
    public Feedback createFeedback(Long userId, Feedback fb) {
        if (fb.getPlayArea() == null && fb.getCombo() == null)
            throw new IllegalArgumentException("Phải chọn PlayArea hoặc Combo để đánh giá.");

        if (fb.getPlayArea() != null && feedbackRepo.existsByUserIdAndPlayAreaId(userId, fb.getPlayArea().getId()))
            throw new RuntimeException("Bạn đã đánh giá khu vực này rồi.");

        if (fb.getCombo() != null && feedbackRepo.existsByUserIdAndComboId(userId, fb.getCombo().getId()))
            throw new RuntimeException("Bạn đã đánh giá combo này rồi.");

        boolean usedService = ticketOrderRepo.findByUserId(userId).stream()
                .flatMap(order -> order.getCombos().stream()) // Trải combo trong từng order
                .anyMatch(combo -> combo.equals(fb.getCombo())); // So sánh combo với combo user muốn đánh giá

        if (fb.getCombo() != null && !usedService)
            throw new RuntimeException("Bạn chưa từng sử dụng combo này.");

        fb.setUser(userRepo.findById(userId).orElseThrow());
        fb.setCreatedAt(LocalDateTime.now());
        return feedbackRepo.save(fb);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll();
    }

    @Override
    public Feedback replyToFeedback(Long feedbackId, String reply) {
        Feedback fb = feedbackRepo.findById(feedbackId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy phản hồi."));

        fb.setReply(reply);
        fb.setRepliedAt(LocalDateTime.now());
        return feedbackRepo.save(fb);
    }
}
