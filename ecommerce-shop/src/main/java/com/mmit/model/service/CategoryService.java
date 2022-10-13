package com.mmit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.model.entity.Category;
import com.mmit.model.repo.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo caterepo;

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return caterepo.findAll();
	}

	public void save(Category cate) {
		
		caterepo.save(cate);
	}

	public void deleteById(int id) {
		
		caterepo.deleteById(id);
	}

	public Object findById(int id) {
		
		return caterepo.findById(id);
	}

	
	
}
