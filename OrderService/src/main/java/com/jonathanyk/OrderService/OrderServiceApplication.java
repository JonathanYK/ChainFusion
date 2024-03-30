package com.jonathanyk.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jonathanyk.OrderService", "com.jonathanyk.chainCommons.exception"})
@Slf4j
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		log.info("******************************");
		log.info("*** order server is up ***");
		log.info("******************************");
	}

}
