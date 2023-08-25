package de.dastekin.zelkulon.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceRegistryMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryMainApplication.class, args);
	}

}
