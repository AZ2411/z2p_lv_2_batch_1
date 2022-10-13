package com.mmit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mmit.model.entity.User;
import com.mmit.model.entity.UserRole;
@Configuration
public class AdminUserCreation {
	@Autowired
	private UserService userServ;
	@Bean
	public CommandLineRunner runner() {
		
		return (args) -> {
			long count = userServ.countUser();
			if(count == 0) {
				User user = new User();
				user.setEmail("admin@gmail.com");
				user.setPassword("123");
				user.setRoles(UserRole.admin);
				user.setPhone("09123456789");
				user.setName("admin");
				
				userServ.save(user);
				
			}
		};
	}
	
}
