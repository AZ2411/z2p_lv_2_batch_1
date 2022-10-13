package com.mmit.security;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mmit.model.entity.User;
import com.mmit.model.repo.UserRepo;
@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	
	

	public long countUser() {
		
		return repo.count();
	}

	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repo.save(user);
		
	}
	
	public User profile(String email) {
		
		return repo.findUserByEmail(email);
	}

	public List<User> findAll() {
		
		return repo.findAll();
	}

	public void deleteById(int id) {
		
		repo.deleteById(id);
	}

	public Object findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	 
}
