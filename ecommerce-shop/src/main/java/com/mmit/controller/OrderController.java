package com.mmit.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mmit.model.entity.Orders;
import com.mmit.model.entity.User;
import com.mmit.model.service.OrderItemService;
import com.mmit.model.service.OrderService;
import com.mmit.security.UserService;

@Controller
public class OrderController {
	@Autowired
	private OrderService ser;
	@Autowired
	private OrderItemService Oiser;
	@Autowired
	private UserService userService;
	@GetMapping("/admin/ordersDetail/{id}")
	public String OrderDetail(@PathVariable("id")long id,Model m) {
		var ordersdetail = ser.findById(id);
		
		//m.addAttribute("order", ordersdetail);
		m.addAttribute("orderProducts", Oiser.findProducts(id));
		return "admin-order-detail";
	}
	@GetMapping("/admin/ordersupdate/{id}")
	public String OrderStageUpdate(@PathVariable("id")long id,Model m) {
		var order = ser.findById(id);
		
		m.addAttribute("order" , order);
		return "admin-order-update";
	}
	@PostMapping("/admin/orderSave")
	public String OrderStageUpdate(@ModelAttribute("order") Orders order) {
		ser.save(order);
		return "redirect:/admin/ordersTable";
	}
	@GetMapping("/admin/myorders") 
	public String myOrders(Principal principal,Model m) {
		User loginUser = userService.profile(principal.getName());
		long userId = loginUser.getId();
		
		System.out.println(loginUser);
		m.addAttribute("ordersList" , ser.findUserById(userId));
		
		return "admin-order-table";
	}
//	@GetMapping("/admin/myprofile") 
//	public String myProfile(Principal principal,Model m) {
//		User loginUser = userService.profile(principal.getName());
//		
//		m.addAttribute("user", loginUser);
//		
//		return "admin-my-profile";
//	}
}
