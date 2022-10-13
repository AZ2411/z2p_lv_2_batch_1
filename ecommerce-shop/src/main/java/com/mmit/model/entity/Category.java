package com.mmit.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name = "categories")

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	
	
	private String photo;
	@CreationTimestamp
	private LocalDate created_at;
	@UpdateTimestamp
	private LocalDate update_at;
	
	
	
	
	public int getId() {
		return id;
	}


	


	


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public LocalDate getCreated_at() {
		return created_at;
	}


	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}


	public LocalDate getUpdate_at() {
		return update_at;
	}


	public void setUpdate_at(LocalDate update_at) {
		this.update_at = update_at;
	}


	
	
	
	public Category() {
		super();
	}
   
}
