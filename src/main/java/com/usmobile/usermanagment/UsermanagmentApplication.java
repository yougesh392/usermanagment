package com.usmobile.usermanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class UsermanagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsermanagmentApplication.class, args);
	}

}
