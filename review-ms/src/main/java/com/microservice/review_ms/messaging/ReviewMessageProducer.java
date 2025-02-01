package com.microservice.review_ms.messaging;

import com.microservice.review_ms.Review;
import com.microservice.review_ms.dto.ReviewMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger logger = LoggerFactory.getLogger(ReviewMessageProducer.class);

    public void sendMessage(Review review){
        ReviewMessage reviewMessage = ReviewMessage.builder().id(review.getId()).description(review.getDescription())
                .rating(review.getRating()).userId(review.getUserId()).productId(review.getProductId()).build();

        logger.info(String.format("Message sent -> %s", reviewMessage.toString()));
        rabbitTemplate.convertAndSend(exchange,routingKey, reviewMessage);
    }
}
