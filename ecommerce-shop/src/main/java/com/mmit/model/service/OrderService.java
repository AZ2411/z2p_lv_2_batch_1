package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mmit.model.entity.Orders;
import com.mmit.model.repo.OrderRepo;

@Service
public class OrderService {
	@Autowired
	private OrderRepo repo;

	public  Orders save(Orders new_order) {
		// TODO Auto-generated method stub
		return repo.save(new_order);
	}

	public List<Orders> findAll() {
		
		return repo.findAll();
	}

	public Object findById(long id) {
		
		return repo.findById(id);
	}

	public List<Orders> findUserById(long id) {
		
		return repo.findOrderByUserId(id);
	}

	

	
}
