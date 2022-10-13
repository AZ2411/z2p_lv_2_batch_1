package com.mmit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mmit.model.service.CategoryService;
import com.mmit.model.service.OrderService;
import com.mmit.model.service.ProductsService;
import com.mmit.security.UserService;

@Controller
public class AdminController {
	@Autowired
	private ProductsService ser;
	@Autowired 
	private CategoryService cateser;
	@Autowired
	private UserService userser;
	@Autowired
	private OrderService orderSer;
	
	
	@GetMapping("/admin/productsTable")
	public String productsTable(Model m) {
		m.addAttribute("productsList", ser.findAll());
		return "admin-products-table";
	}
	@GetMapping("/admin/categoriesTable")
	public String categoriesTable(Model m) {
		m.addAttribute("categoriesList", cateser.findAll());
		return "admin-category-table";
	}
	@GetMapping("/admin/usersTable")
	public String userTable(Model m) {
		m.addAttribute("users" , userser.findAll());
		return "admin-user-table";
	}
	@GetMapping("/admin/ordersTable")
	public String orderTables(Model m) {
		m.addAttribute("ordersList" , orderSer.findAll());
		return "admin-order-table";
	}
}
