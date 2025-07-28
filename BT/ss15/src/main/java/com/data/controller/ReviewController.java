package com.data.controller;

import com.data.model.entity.Review;
import com.data.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@RequestParam Long productId,
                                            @RequestParam int rating,
                                            @RequestParam String comment) {
        return ResponseEntity.ok(reviewService.createReview(productId, comment, rating));
    }

    @GetMapping("/products/{id}/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(id));
    }
}
