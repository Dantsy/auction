package ru.otus.spring.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuctionAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionAuthorizationServerApplication.class, args);
	}
}