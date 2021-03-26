package com.udemy.myProject;

import com.udemy.myProject.Managers.HoaxService;
import com.udemy.myProject.Managers.UserServiceManager;
import com.udemy.myProject.Models.Hoax;
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
	CommandLineRunner createInitialUsers(UserServiceManager manager, HoaxService hoaxService){
		return (args -> {
			for(int i = 1; i<=25; i++){
				User user = new User();
				user.setUsername("user"+i);
				user.setDisplayName("display"+i);
				user.setPassword("P4ssword");
				manager.save(user);
				for(int j = 1; j<=23; j++){
					Hoax hoax = new Hoax();
					hoax.setContent(user.getDisplayName() + " hoax - " + j);
					hoaxService.save(hoax,user);
				}
			}

		});
	}
}
