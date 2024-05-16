package com.usmobile.usermanagment;

import com.usmobile.usermanagment.DAO.UserDAO;
import com.usmobile.usermanagment.repository.UserRepository;
import com.usmobile.usermanagment.utils.EncryptionUtil;
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
	CommandLineRunner runner(UserRepository userRepository) {
		return args -> {


//			for (int i = 1; i <= 10; i++) {
//				UserDAO user = new UserDAO();
//				user.setFirstName("Test" + i);
//				user.setLastName("User");
//				user.setEmail("test" + i + "@example.com");
//				user.setPassword(EncryptionUtil.encode("password"));
//				user.setPhoneNumber("123456789" + i);
//				userRepository.save(user);
//			}
		};
	}
}
