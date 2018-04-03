package com.uzak.tutoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages= {"com.uzak.tutoring"})
public class Start {
	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}
}
