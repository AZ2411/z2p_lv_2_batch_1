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
import com.mmit.model.entity.Product;
import com.mmit.model.service.CategoryService;
import com.mmit.model.service.ProductsService;

@Controller
public class ProductsController {
	@Autowired
	private ProductsService ser;
	@Autowired 
	private CategoryService cateser;
	
	@GetMapping("/products/detail")
	public String detailProducts() {
		
		return "product-detail";
	}
	
	@GetMapping("/admin/productsAdd")
	public String productsAdd(Model m) {
		m.addAttribute("products",  new Product());
		m.addAttribute("categories", cateser.findAll());
		return "admin-products-add";
	}
	@PostMapping("/admin/productsSave")
	public String productsSave(@ModelAttribute("products") Product prod, @RequestParam("photoName")MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if((prod.getId() == 0 || prod.getId() != 0) && !fileName.equals(""))
		{
			prod.setPhoto(fileName);
		}
		var saveProduct = ser.save(prod);
		if(!"".equals(fileName))
		{
			String uploadDir = "uploads/" + saveProduct.getId();
			
			FileUploadUtil.savePhoto(uploadDir, fileName , file);
		}
		return "redirect:/admin/productsTable";
	}
	@GetMapping("/admin/productsDelete/{id}")
	public String productsDelete(@PathVariable("id") long id) {
		ser.deleteById(id);
		
		return "redirect:/admin/productsTable";
	}
	@GetMapping("/admin/productsupdate/{id}")
	public String productsUpdate(@PathVariable("id")  long id,Model m) {
		var product = ser.findById(id);
		m.addAttribute("products", product);
		m.addAttribute("categories", cateser.findAll());
		return "admin-products-add";
	}
	@ModelAttribute
	public void assignDefualtModel(Model m) {
		
		m.addAttribute("page" , "products");
	}
	
}
