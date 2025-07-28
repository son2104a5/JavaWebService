package com.data.service;

import com.data.model.entity.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Long productId, String comment, int rating);
    List<Review> getReviewsByProduct(Long productId);
}
