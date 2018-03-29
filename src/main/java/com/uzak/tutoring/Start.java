package com.uzak.tutoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 */
@SpringBootApplication
@ComponentScan(basePackages= {"com.uzak.tutoring"})
public class Start {
	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}
}
