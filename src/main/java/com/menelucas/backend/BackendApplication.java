package com.menelucas.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		ApplicationContext apc = SpringApplication.run(BackendApplication.class, args);
		for (String name : apc.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

}
