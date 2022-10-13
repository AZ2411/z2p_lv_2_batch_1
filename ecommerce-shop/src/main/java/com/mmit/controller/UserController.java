package com.mmit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mmit.model.entity.User;
import com.mmit.model.entity.UserRole;
import com.mmit.security.UserService;

@Controller
public class UserController {
		@Autowired
		private UserService uSer;
		@GetMapping("/register")
		public String registerPage(Model m) {
			
			m.addAttribute("users", new User());
			return "register";
		}
		
		@PostMapping("/admin/users/save")
		public String saveUser( @ModelAttribute("user") User user) {
			
			if(user.getRoles() == null) {
				
				user.setRoles(UserRole.customer);
				
			}
			
			uSer.save(user);
			
			System.out.println("USer is :" + uSer);
			return "redirect:/shop";
		}
		@GetMapping("/admin/usersDelete/{id}")
		public String productsDelete(@PathVariable("id") int id) {
			uSer.deleteById(id);
			
			return "redirect:/admin/usersTable";
		}
		@GetMapping("/admin/usersupdate/{id}")
		public String productsUpdate(@PathVariable("id")  int id,Model m) {
			var user = uSer.findById(id);
			m.addAttribute("users", user);
			
			return "register";
		}
		
		
		
		
}
