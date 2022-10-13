package com.mmit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mmit.controller.request.OrderProductData;
import com.mmit.controller.request.OrderReceiverData;
import com.mmit.controller.request.OrderRequestData;
import com.mmit.model.entity.OrderItem;
import com.mmit.model.entity.OrderStatus;
import com.mmit.model.entity.Orders;
import com.mmit.model.entity.User;
import com.mmit.model.service.OrderService;
import com.mmit.model.service.ProductsService;
import com.mmit.security.UserService;

@Controller
public class CartController {
	@Autowired
	private ProductsService service;
	@Autowired
	private OrderService ordService;
	@Autowired
	private UserService userService;
	@GetMapping("/cart/detail")
	public String cratpage() {
		
		return "cart";
	}
	
	@GetMapping("/cart/checkout")
	public String checkoutPage(ModelMap map, Principal principal) {
		User loginUser = userService.profile(principal.getName());
		
		map.put("name", loginUser.getName());
		map.put("phone", loginUser.getPhone());
		map.put("email", loginUser.getEmail());
		map.put("address", loginUser.getAddres());
		return "checkout";
	}
	
	@PostMapping("/cart/place-order")
	public @ResponseBody String placeOrder(@RequestBody OrderRequestData obj, Principal principal) {
		try {
			System.out.println(obj.getOrderItems());
			System.out.println(obj.getReceiver());
//			correct
			
			OrderReceiverData receiverData = obj.getReceiver();
			List<OrderProductData> itemList = obj.getOrderItems();
			
			
			
			//created new orders
			Orders new_order= new Orders(); 
			new_order.setStatus(OrderStatus.pending);
			new_order.setShippingAddres(receiverData.getAddress());
			new_order.setShippingEmail(receiverData.getEmail());
			new_order.setShippingName(receiverData.getName());
			new_order.setShippingPhone(receiverData.getPhone());
			new_order.setCustomer(userService.profile(principal.getName()));
			
			//add order item
			for(var item: itemList) {
				
				var product = service.findById(item.getProductId());
				var order_item = new OrderItem();
				order_item.setProduct(product);
				order_item.setQuantity(item.getQty());
				
				new_order.addOrderItem(order_item);
				}
			System.out.println("Orders : " + new_order.toString());
			//save order to db
			Orders savedOrder = ordService.save(new_order);
			return savedOrder.getId() + "";
			
		} catch (Exception e) {
			return"";
		}
		
	}
	
}
