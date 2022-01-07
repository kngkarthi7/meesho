package com.meeshoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.meeshoapi"})
@EntityScan(basePackages = {"com.meeshoapi"})
@EnableAutoConfiguration
public class MeeshoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeeshoApplication.class, args);
	}

}
