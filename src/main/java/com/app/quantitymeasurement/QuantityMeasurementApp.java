package com.app.quantitymeasurement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuantityMeasurementApp {

    public static void main(String[] args) {
        SpringApplication.run(QuantityMeasurementApp.class, args);
    }

    @Bean
    CommandLineRunner runDemo() {
        return args -> {
            QuantityDemo.runDemo();
        };
    }
}