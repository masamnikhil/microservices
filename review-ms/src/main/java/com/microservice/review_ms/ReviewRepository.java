package com.microservice.review_ms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<List<Review>> findAllByProductId(Long productId);
    Optional<Set<Review>> findAllByUserId(Long userId);
}
