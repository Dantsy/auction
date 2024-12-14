package ru.otus.spring.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "ru.otus.spring.project.feign")
public class AuctionClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionClientApplication.class, args);
        log.info("let's start: http://localhost:8080/auction");
    }
}
