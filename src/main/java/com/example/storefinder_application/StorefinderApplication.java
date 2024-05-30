package com.example.storefinder_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class StorefinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorefinderApplication.class, args);
	}
	@GetMapping("")
	public String testApi() {
		return "abcde";
	}
}
