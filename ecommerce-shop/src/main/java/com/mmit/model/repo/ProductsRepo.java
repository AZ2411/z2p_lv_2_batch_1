package com.mmit.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.model.entity.Product;

public interface ProductsRepo  extends JpaRepository<Product, Long>{

}
