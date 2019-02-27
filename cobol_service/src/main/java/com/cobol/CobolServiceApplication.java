package com.cobol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@EnableEurekaClient
@SpringBootApplication
public class CobolServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CobolServiceApplication.class, args);
    }
}