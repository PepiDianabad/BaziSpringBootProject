package com.example.springbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan("com.example.springbootapp.Models")
public class SpringBootAppApplication {

    public static void main(String[] args) {

        DataSourceConfig.dataSource();
        SpringApplication.run(SpringBootAppApplication.class, args);
    }
}
