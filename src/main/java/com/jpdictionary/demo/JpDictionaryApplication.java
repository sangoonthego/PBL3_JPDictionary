package com.jpdictionary.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.jp-dictionary.demo")
public class JpDictionaryApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpDictionaryApplication.class, args);
	}
}
