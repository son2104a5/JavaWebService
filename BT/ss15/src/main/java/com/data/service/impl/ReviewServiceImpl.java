package com.data.service.impl;

import com.data.model.entity.*;
import com.data.repository.*;
import com.data.service.ReviewService;
import com.data.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    @Override
    public Review createReview(Long productId, String comment, int rating) {
        String username = SecurityUtils.getCurrentUsername();
        User user = userRepository.findByEmail(username).orElseThrow();

        boolean hasPurchased = orderItemRepository.existsByProductIdAndOrderUser(productId, user);
        if (!hasPurchased) {
            throw new RuntimeException("Bạn chưa mua sản phẩm này nên không thể đánh giá.");
        }

        Product product = productRepository.findById(productId).orElseThrow();
        Review review = Review.builder()
                .comment(comment)
                .rating(rating)
                .createdDate(LocalDateTime.now())
                .user(user)
                .product(product)
                .build();
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        return reviewRepository.findByProduct(product);
    }
}
