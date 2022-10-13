package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entity.Product;
import com.mmit.model.repo.ProductsRepo;



@Service
public class ProductsService {
	
	
	@Autowired
	private ProductsRepo repo;

	public List<Product> findAll() {
		
		return repo.findAll();
	}

	public Product findById(long pId) {
		
		return repo.findById(pId).orElse(new Product());
	}

	public Product save(Product prod) {
		
		return repo.save(prod);
	}

	public void deleteById(long id) {
		repo.deleteById(id);
		
	}
	
	
	
}
