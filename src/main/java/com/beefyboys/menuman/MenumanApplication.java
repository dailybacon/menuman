package com.beefyboys.menuman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MenumanApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenumanApplication.class, args);
	}

}

