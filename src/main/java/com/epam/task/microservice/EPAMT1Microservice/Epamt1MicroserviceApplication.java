package com.epam.task.microservice.EPAMT1Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@SpringBootApplication
@EnableDiscoveryClient
public class Epamt1MicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Epamt1MicroserviceApplication.class, args);
	}

}
