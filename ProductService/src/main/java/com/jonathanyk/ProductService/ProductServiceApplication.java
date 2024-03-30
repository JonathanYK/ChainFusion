package com.jonathanyk.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.jonathanyk.ProductService", "com.jonathanyk.chainCommons.exception"})
@Slf4j
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
		log.info("******************************");
		log.info("*** product service is up ***");
		log.info("******************************");
	}

}
