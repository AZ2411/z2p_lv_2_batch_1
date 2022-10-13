package com.mmit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mmit.FileUploadUtil;
import com.mmit.model.entity.Category;
import com.mmit.model.entity.Product;
import com.mmit.model.service.CategoryService;
import com.mmit.model.service.ProductsService;

@Controller
public class CategoryController {
	@Autowired
	private ProductsService ser;
	@Autowired 
	private CategoryService cateser;
	
	@GetMapping("/admin/categoriesAdd")
	public String categoriesAdd(Model m) {
		
		m.addAttribute("categories", new Category());
		return "admin-categoryadd";
	}
	@PostMapping("/admin/categoriesSave")
	public String categoriesSave(@ModelAttribute("categories") Category cate) {
			cateser.save(cate);
		return "redirect:/admin/categoriesTable";
	}
	@GetMapping("/admin/categoriesDelete/{id}")
	public String productsDelete(@PathVariable("id") int id) {
		cateser.deleteById(id);
		
		return "redirect:/admin/categoriesTable";
	}
	@GetMapping("/admin/categoriesupdate/{id}")
	public String productsUpdate(@PathVariable("id")  int id,Model m) {
		var category = cateser.findById(id);
		m.addAttribute("categories", category);
		
		return "admin-categoryadd";
	}
	@ModelAttribute
	public void assignDefualtModel(Model m) {
		
		m.addAttribute("page" , "categories");
	}
	
	
}
