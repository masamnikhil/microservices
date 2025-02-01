package com.microservice.product_ms.messaging;

import com.microservice.product_ms.ProductService;
import com.microservice.product_ms.dto.ReviewMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewMessageConsumer {

    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ReviewMessageConsumer.class);

    @RabbitListener(queues = {"productRatingQueue"})
    public void consumeMessage(ReviewMessage reviewMessage){

        logger.info(String.format("Message received -> %s", reviewMessage.toString()));
        productService.updateProductRating(reviewMessage);
    }
}
