package com.mmit.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmit.model.entity.OrderItem;
import com.mmit.model.entity.User;

public interface OrderItemrepo extends JpaRepository<OrderItem, Integer>{
	@Query("SELECT o FROM OrderItem o WHERE o.orders = :input")
	List<OrderItem> findOrderItemByOrderId(@Param("input") long orderId);
}
