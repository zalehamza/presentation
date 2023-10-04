package com.fraud.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FraudApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudApplication.class, args);
	}

}
