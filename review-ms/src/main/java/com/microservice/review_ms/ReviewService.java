package com.microservice.review_ms;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public interface ReviewService {

    @Transactional
    public boolean saveReview(Review review);
    public Review getReview(Long id);
    public List<Review> getReviewsByProductId(Long productId);

    public Set<Review> getReviewsByUserId(Long userId);

    public boolean editReview(Long id);


}
