package com.usmobile.usermanagment;

import com.usmobile.usermanagment.model.User;
import com.usmobile.usermanagment.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UsermanagmentApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UsermanagmentApplication.class, args);

		UserRepository userRepository = context.getBean(UserRepository.class);
		User user = new User();
		user.setEmail("dummy@example.com");
		user.setFirstName("Dummy");
		user.setLastName("User");
		user.setPassword("password");
		user.setPhoneNumber("1234567890");

		userRepository.save(user);
	}

}
