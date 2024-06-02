package com.example.storefinder_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByStoreId(String storeId) {
        return reviewRepository.findByStoreId(storeId);
    }

    public double getAverageRating(String storeId) {
        List<Review> reviews = reviewRepository.findByStoreId(storeId);
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0); // Return 0.0 if there are no reviews
    }
}
