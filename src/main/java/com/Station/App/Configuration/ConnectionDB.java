package com.Station.App.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Configuration
public class ConnectionDB {
    private static final String URL = System.getenv("url");
    private static final String USER = System.getenv("user");
    private static String PASSWORD = System.getenv("password");
    @Bean
    public static Connection getConnection() throws SQLException {
        System.out.println(URL);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
