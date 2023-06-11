package com.petziferum.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // This annotation is required to make the service register itself with the discovery-server
public class ProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

}
