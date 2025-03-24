package com.laligainfo.laligainfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.laligainfo.laligainfo.vo")
@EnableJpaRepositories(basePackages = {"com.laligainfo.laligainfo.repository"})
public class LaLigaInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaLigaInfoApplication.class, args);
	}

}
