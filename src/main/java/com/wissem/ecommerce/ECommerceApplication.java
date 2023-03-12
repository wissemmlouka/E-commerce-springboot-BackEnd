package com.wissem.ecommerce.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.wissem.ecommerce.jwt.ECommerceApplication.class, args);
	}

}
