package com.example.springbootapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.Scanner;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public static DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter database URL: ");
        String url = scanner.nextLine();

        System.out.print("Enter database username: ");
        String username = scanner.nextLine();

        System.out.print("Enter database password: ");
        String password = scanner.nextLine();

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        // Не забравяйте да настроите driver-class-name за вашата база данни, например:
        // dataSource.setDriverClassName("org.postgresql.Driver");

        scanner.close();

        return dataSource;
    }
}