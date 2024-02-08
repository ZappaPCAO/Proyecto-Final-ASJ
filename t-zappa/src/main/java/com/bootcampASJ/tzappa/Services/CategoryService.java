package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.ExceptionCustom;

import com.bootcampASJ.tzappa.Models.Category;

import com.bootcampASJ.tzappa.Repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getCategories() {
		return this.categoryRepository.findAll();	
	}
	
	public List<Category> getCategoriesByActive(){
		return this.categoryRepository.findByIsDeletedFalse();
	}
	
	public Category getCategoryById(Integer id) {
		return this.categoryRepository.findById(id).get();
	}
	
	@Transactional
	public Category newCategory(Category category) {	

		List<Category> storedCategorys = this.categoryRepository.findAll();
				
		for(Category cat : storedCategorys) {
			if (category.getName().equals(cat.getName())) { // Si quiere insertar un registro que ya esta
				throw new ExceptionCustom("Ya hay un registro con ese nombre.");
			}
		}
		
		this.categoryRepository.save(category);
		return category;
	}
	
	@Transactional
	public Category updateCategory(Category category){
						
		Category storedCategory = this.categoryRepository.findById(category.getId()).get();
		List<Category> storedCategorys = this.categoryRepository.findAll();
		
		if(storedCategory == null)  
			throw new ExceptionCustom("El registro no fue encontrado en la base de datos.");
		
		if (category.equals(storedCategory)) 
			throw new ExceptionCustom("Debes cambiar algun dato.");		
		
		for(Category cat : storedCategorys) {
			if (category.getName().equals(cat.getName())) { // Si quiere insertar un registro que ya esta
				throw new ExceptionCustom("Ya hay un registro con ese nombre.");
			}
		}
		
		category.setUpdatedAt(LocalDateTime.now());
		this.categoryRepository.save(category);
		return category; 
	}
	
	@Transactional
	public Category deleteCategory(Integer id) {
		
		Category category = this.categoryRepository.findById(id).get();
		
		if(category == null)  
			throw new ExceptionCustom("El registro no fue encontrado en la base de datos.");
		if(category.getIsDeleted()) 
			throw new ExceptionCustom("Este registro ya esta eliminado.");
	
		category.setUpdatedAt(LocalDateTime.now());
		category.setIsDeleted(true);
		
		return this.categoryRepository.save(category);
	}
	
	@Transactional
	public Category rescueCategory(Integer id) {
		Category category = this.categoryRepository.findById(id).get();
		
		if(category == null)  
			throw new ExceptionCustom("La entidad no fue encontrada en la base de datos");
		if(!category.getIsDeleted()) 
			throw new ExceptionCustom("Este registro ya esta salvado.");
	
		category.setUpdatedAt(LocalDateTime.now());
		category.setIsDeleted(false);
		
		return this.categoryRepository.save(category);
	}
}
