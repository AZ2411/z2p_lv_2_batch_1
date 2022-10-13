package com.mmit.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmit.model.entity.Orders;
import com.mmit.model.entity.User;

public interface OrderRepo extends JpaRepository<Orders, Long>{
	@Query("SELECT o FROM Orders o WHERE o.customer = :input")
	List<Orders> findOrderByUserId(@Param("input") long id);
}
