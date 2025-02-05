package com.example.users_service_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.users_service_api", "com.example.auth_service_api"})
@EnableJpaRepositories(basePackages = "com.example.auth_service_api.repositories")
@EntityScan(basePackages = "com.example.auth_service_api.commons.entities")
public class UsersServiceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApiApplication.class, args);
    }
}