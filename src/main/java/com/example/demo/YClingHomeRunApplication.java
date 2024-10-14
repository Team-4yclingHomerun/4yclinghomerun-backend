package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.example")
@ConfigurationPropertiesScan(basePackages = "com.example")
public class YClingHomeRunApplication {

	public static void main(String[] args) {
		SpringApplication.run(YClingHomeRunApplication.class, args);
	}

}
