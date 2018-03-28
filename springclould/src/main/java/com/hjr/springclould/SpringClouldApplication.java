package com.hjr.springclould;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringClouldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClouldApplication.class, args);
	}
}
