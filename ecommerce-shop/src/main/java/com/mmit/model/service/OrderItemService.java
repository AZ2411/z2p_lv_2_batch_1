package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entity.OrderItem;
import com.mmit.model.repo.OrderItemrepo;

@Service
public class OrderItemService {
@Autowired
private OrderItemrepo repo;

public List<OrderItem> findProducts(long id) {
	
	return  repo.findOrderItemByOrderId(id);
}


}
