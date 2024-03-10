package com.example.vktestproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VkTestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkTestProjectApplication.class, args);
	}

}
