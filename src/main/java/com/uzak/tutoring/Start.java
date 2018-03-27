package com.uzak.tutoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EntityScan(basePackages= {"com.uzak.tutoring.entity"})
@ComponentScan(basePackages= {"com.uzak.tutoring"})
public class Start {
	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}
}
