package com.microservice.user_ms.clients;

import com.microservice.user_ms.external.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderClient {

    @GetMapping("/orders/{id}")
    List<Order> getOrders(@PathVariable("id") Long id);
}
