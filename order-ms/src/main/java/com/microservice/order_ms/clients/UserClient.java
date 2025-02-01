package com.microservice.order_ms.clients;

import com.microservice.order_ms.external.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/getuser/{username}")
    public User getUser(@PathVariable("username") String username);
}
