package com.usmobile.usermanagment;

import com.usmobile.usermanagment.model.UserDTO;
import com.usmobile.usermanagment.service.UserManagementService;
import com.usmobile.usermanagment.utils.RandomNumberGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class UserManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagmentApplication.class, args);
	}

	/*
	* loads the test data into the database
	* */
	@Bean
	CommandLineRunner runner(UserManagementService userService) {
		return args -> {
			for (int i = 1; i <= 10; i++) {
				UserDTO user = new UserDTO();
				user.setFirstName("Test");
				user.setLastName("User");
				user.setEmail("test" + i + "@example.com");
				user.setPassword("password" + i);
				user.setPhoneNumber(RandomNumberGenerator.generateRandomCTN());
				userService.createUser(user);
			}
		};
	}
}
