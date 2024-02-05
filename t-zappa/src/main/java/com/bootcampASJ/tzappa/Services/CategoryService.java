package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.IdenticalRecord;

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
	public Optional<Category> newCategory(Category category) {	
	    try {
	        return Optional.ofNullable(this.categoryRepository.save(category));
	    } catch (DataIntegrityViolationException error) {
	    	System.out.println("Clave duplicada detectada");
            return Optional.empty();
	    }
	}
	
	@Transactional
	public Category updateCategory(Category category){
						
		Category storedCategory = this.categoryRepository.findById(category.getId()).get();
		
		if (!category.equals(storedCategory)) {
			category.setUpdatedAt(LocalDateTime.now());
			this.categoryRepository.save(category);
			return category; 			
		}
		
		throw new IdenticalRecord("El registro que estás intentando editar es idéntico al existente en la base de datos.");
	}
	
	@Transactional
	public Category deleteCategory(Integer id) {
		
		Category category = this.categoryRepository.findById(id).get();
		
		if(category == null)  
			throw new IdenticalRecord("La entidad no fue encontrada en la base de datos");
		if(category.getIsDeleted()) 
			throw new IdenticalRecord("Este registro ya esta eliminado.");
	
		category.setUpdatedAt(LocalDateTime.now());
		category.setIsDeleted(true);
		
		return this.categoryRepository.save(category);
	}
	
	@Transactional
	public Category rescueCategory(Integer id) {
		Category category = this.categoryRepository.findById(id).get();
		
		if(category == null)  
			throw new IdenticalRecord("La entidad no fue encontrada en la base de datos");
		if(!category.getIsDeleted()) 
			throw new IdenticalRecord("Este registro ya esta salvado.");
	
		category.setUpdatedAt(LocalDateTime.now());
		category.setIsDeleted(false);
		
		return this.categoryRepository.save(category);
	}
}
