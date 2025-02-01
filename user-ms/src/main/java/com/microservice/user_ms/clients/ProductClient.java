package com.microservice.user_ms.clients;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name = "product-ms")
public interface ProductClient {

}
