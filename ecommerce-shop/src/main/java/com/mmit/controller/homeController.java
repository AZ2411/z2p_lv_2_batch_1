package com.mmit.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import com.mmit.model.entity.User;
import com.mmit.model.service.CategoryService;
import com.mmit.model.service.ProductsService;
import com.mmit.security.UserService;

@Controller
public class homeController {
	@Autowired
	private ProductsService Pser;
	@Autowired
	private CategoryService Cser;
	@Autowired
	private UserService User;
		@GetMapping("/")
		public String homePage() {
			
			return "redirect:/shop";
		}
		@GetMapping("/shop")
		public String goShop(Model m) {
			m.addAttribute("productList" , Pser.findAll());
			m.addAttribute("categoriesList" , Cser.findAll());
			return "index";
			
		}
		
		@GetMapping("/shop/products/{id}")
		public String singleProducts(Model m, @PathVariable("id") long pId) {
			m.addAttribute("product" , Pser.findById(pId));
			return "product-detail";
		}
		
		@GetMapping("/login")
		public String loginPage() {
			
			return "login";
		}
		
		@GetMapping("/shop/orders")
		public String myOrder() {
			// TODO Auto-generated method stub
			return "my-order";
		}
		@GetMapping("/admin/homePage")
		public String adminHomePage() {
			
			
			return "adminHomePage";
		}
		@GetMapping("/admin/tables")
		public String admintablesPage() {
			
			
			return "tables";
		}
		@GetMapping("/403")
		public String deniedPage() {
			return "401";
		}
		
		
}
