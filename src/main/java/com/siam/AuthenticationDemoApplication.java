package com.siam;

import com.siam.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.siam.model.User;
@SpringBootApplication
public class AuthenticationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			if (userRepository.findByUsername("intern").isEmpty()) {
				userRepository.save(new User(null, "intern", encoder.encode("password123"), "USER"));
			}
			if (userRepository.findByUsername("admin").isEmpty()) {
				userRepository.save(new User(null, "admin", encoder.encode("admin123"), "ADMIN"));
			}
		};
	}

}
