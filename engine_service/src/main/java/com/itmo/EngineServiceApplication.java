package com.itmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EngineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineServiceApplication.class, args);
    }
}