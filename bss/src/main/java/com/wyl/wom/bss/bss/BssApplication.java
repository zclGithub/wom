package com.wyl.wom.bss.bss;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.wyl.wom")
public class BssApplication {
	public static void main(String[] args) {
		SpringApplication.run(BssApplication.class, args);
	}
}
