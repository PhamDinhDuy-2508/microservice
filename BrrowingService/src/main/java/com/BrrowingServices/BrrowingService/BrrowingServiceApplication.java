package com.BrrowingServices.BrrowingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BrrowingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrrowingServiceApplication.class, args);
	}

}
