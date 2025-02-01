package com.microservice.order_ms;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/saveorder/{username}")
    public ResponseEntity<String> saveOrder(@RequestBody Order order, @PathVariable("username") String username){
        if(orderService.saveOrder(order,username)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Order Created Successfully");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
