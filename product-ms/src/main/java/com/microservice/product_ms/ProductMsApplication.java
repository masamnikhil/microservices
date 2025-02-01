package com.microservice.product_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMsApplication.class, args);
	}

}
