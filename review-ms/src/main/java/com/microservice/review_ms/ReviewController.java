package com.microservice.review_ms;

import com.microservice.review_ms.messaging.ReviewMessageProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    private final ReviewMessageProducer reviewMessageProducer;

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @PostMapping("/savereview/{userId}/{productId}")
    private ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable("userId") Long userId,
                                             @PathVariable("productId") Long productId){
        review.setUserId(userId);
        review.setProductId(productId);
        if(reviewService.saveReview(review)) {
            reviewMessageProducer.sendMessage(review);
            return ResponseEntity.status(HttpStatus.CREATED).body("review saved Successfully");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred, please check properly");
    }

    @GetMapping("/getreview/{id}")
    private ResponseEntity<Review> getReview(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReview(id));
        }
        catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{productId}")
    private ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable("productId") Long productId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewsByProductId(productId));
        }
        catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/averageRating")
    public double getAverageRating(@RequestParam(name = "productId") Long productId){

        logger.info(String.format("productId -> %s",productId.toString()));
       List<Review> reviewList = reviewService.getReviewsByProductId(productId);



       return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }

}
