package com.bootcampASJ.tzappa.Controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcampASJ.tzappa.ErrorHandler;

import com.bootcampASJ.tzappa.Models.Category;

import com.bootcampASJ.tzappa.Services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping // [GET] localhost:8080/proveedores
	public ResponseEntity<Object> getCategories() {		
		return ResponseEntity.ok( this.categoryService.getCategories() );
	}
	
	@GetMapping("/actives")
	public ResponseEntity<Object> getProvidersByActive() {		
		return ResponseEntity.ok( this.categoryService.getCategoriesByActive() );
	}
	
	@GetMapping("/{id}") // [GET] localhost:8080/proveedores
	public ResponseEntity<Object> getCategoryById(@PathVariable Integer id) {		
		return ResponseEntity.ok( this.categoryService.getCategoryById(id) );
	}
	
	@PostMapping
	public ResponseEntity<Object> newCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) {
		        Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
		        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		    }
	
		Optional<Category> result = this.categoryService.newCategory(category);

	    if (result.isPresent()){
	        return new ResponseEntity<>(result.get(), HttpStatus.OK);
	    }else{
	        Map<String, String> error = Collections.singletonMap("Error", "Error en la integridad de datos. (Ej. Campo Unique)");
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category,
													BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Category result = this.categoryService.updateCategory(category);
			
			return new ResponseEntity<>(result, HttpStatus.OK);			
		}catch(Exception error) {
			 return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable Integer id){
		try {
			Category result = this.categoryService.deleteCategory(id);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/rescue/{id}")
	public ResponseEntity<Object> rescueCategory(@PathVariable Integer id){
		try {
			Category result = this.categoryService.rescueCategory(id);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
	
}
