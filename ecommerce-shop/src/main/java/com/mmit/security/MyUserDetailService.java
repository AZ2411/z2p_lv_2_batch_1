package com.mmit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mmit.model.entity.User;
import com.mmit.model.repo.UserRepo;

public class MyUserDetailService	implements UserDetailsService{
	@Autowired
	private UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = repo.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid");
		}
		
		UserDetails userDetail = new MyUserDetails(user); 
		
		return userDetail;
	}
	
}
