package com.api.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/reviewFallBack")
    public ResponseEntity<String> orderServiceFallback(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("review service unavailable. Please try again later");
    }

    @RequestMapping("/userFallBack")
    public ResponseEntity<String> userServiceFallback(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("user service unavailable. Please try again later");
    }

    @RequestMapping("/productFallBack")
    public ResponseEntity<String> productServiceFallback(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("product service unavailable. Please try again later");
    }
}
