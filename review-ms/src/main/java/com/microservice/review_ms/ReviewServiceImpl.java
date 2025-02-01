package com.microservice.review_ms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    @Override
    public boolean saveReview(Review review) {
        reviewRepository.save(review);
        return true;
    }

    @Override
    public Review getReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return review;
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProductId(productId).orElseThrow(() -> new RuntimeException());
        return reviews;
    }

    @Override
    public Set<Review> getReviewsByUserId(Long userId) {
        Set<Review> reviews = reviewRepository.findAllByUserId(userId).orElseThrow(() -> new RuntimeException());
        return reviews;
    }

    @Override
    public boolean editReview(Long id) {
        return false;
    }
}
