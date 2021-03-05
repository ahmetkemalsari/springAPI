package com.udemy.myProject;

import com.udemy.myProject.Managers.UserServiceManager;
import com.udemy.myProject.Models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner createInitialUsers(UserServiceManager manager){
		return (args -> {
			User user = new User();
			user.setUsername("Kemal");
			user.setDisplayName("root");
			user.setPassword("P4ssword");
			manager.save(user);
		});
	}
}
