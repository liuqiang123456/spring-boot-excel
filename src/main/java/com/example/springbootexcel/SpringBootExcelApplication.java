package com.example.springbootexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages={"com.example.springbootexcel.dao"})
@ComponentScan(value={"com.*","com.example.springbootexcel.dao"})
@SpringBootApplication
@EntityScan("com.example.springbootexcel.model.**")
public class SpringBootExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExcelApplication.class, args);
    }

}
