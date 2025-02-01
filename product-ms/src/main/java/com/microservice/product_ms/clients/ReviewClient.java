package com.microservice.product_ms.clients;

import com.microservice.product_ms.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEW-SERVICE", configuration = FeignClientConfig.class)
public interface ReviewClient {

    @GetMapping("/review/averageRating")
    Double getAverageRatingForProduct(@RequestParam("productId") Long productId);
}
