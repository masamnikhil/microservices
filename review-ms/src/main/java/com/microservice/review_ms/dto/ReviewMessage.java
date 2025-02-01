package com.microservice.review_ms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewMessage {

    private Long id;
    private String description;
    private Long rating;
    private Long userId;
    private Long productId;
}
