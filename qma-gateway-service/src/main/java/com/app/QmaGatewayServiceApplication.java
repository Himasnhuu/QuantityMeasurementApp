package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.app.gatewayservice")
public class QmaGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QmaGatewayServiceApplication.class, args);
	}

}
