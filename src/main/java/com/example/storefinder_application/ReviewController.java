package com.example.storefinder_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{storeId}/{userId}")
    public Review addReview(@RequestBody Review review, @PathVariable String storeId, @PathVariable String userId) {
        review.setStoreId(storeId);
        review.setUserId(userId);
        return reviewService.addReview(review);
    }

    @GetMapping("/store/{storeId}")
    public List<Review> getReviewsByStoreId(@PathVariable String storeId) {
        return reviewService.getReviewsByStoreId(storeId);
    }

    @GetMapping("/store/{storeId}/average-rating")
    public double getAverageRating(@PathVariable String storeId) {
        return reviewService.getAverageRating(storeId);
    }
}
