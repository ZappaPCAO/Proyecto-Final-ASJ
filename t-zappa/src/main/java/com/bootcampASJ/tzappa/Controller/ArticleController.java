package com.bootcampASJ.tzappa.Controller;

import java.util.Map;

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

import com.bootcampASJ.tzappa.Models.Article;
import com.bootcampASJ.tzappa.Services.ArticleService;
import com.bootcampASJ.tzappa.utils.ErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	@GetMapping // [GET] localhost:8080/proveedores
	public ResponseEntity<Object> getProviders() {		
		return ResponseEntity.ok( this.articleService.getArticles() );
	}
	
	@GetMapping("/actives")
	public ResponseEntity<Object> getProvidersByActive() {		
		return ResponseEntity.ok( this.articleService.getArticlesByActive() );
	}
	
	@GetMapping("/{id}") // [GET] localhost:8080/proveedores/3
	public ResponseEntity<Object> getProviderById(@PathVariable Integer id) {
		return ResponseEntity.ok( this.articleService.getArticleById(id) );
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Object> getArticlesByCategory(@PathVariable Integer id){
		
		if(id == 0) {
			return ResponseEntity.ok( this.articleService.getArticles() );
		}
		
		return ResponseEntity.ok(this.articleService.getArticlesByCategory(id));
	}
	
	@GetMapping("/provider/{id}")
	public ResponseEntity<Object> getArticlesByProvider(@PathVariable Integer id){
		
		if(id == 0) {
			return ResponseEntity.ok(this.articleService.getArticles());
		}
		
		return ResponseEntity.ok(this.articleService.getArticlesByProvider(id));
	}
	
	@GetMapping("/provider/{id}/actives")
	public ResponseEntity<Object> getArticlesActivesByProvider(@PathVariable Integer id) {		
		return ResponseEntity.ok( this.articleService.getArticlesActivesByProvider(id) );
	}
	
	
	@PostMapping
	public ResponseEntity<Object> newArticle(@Valid @RequestBody Article article, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
	        Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Article result = this.articleService.newArticle(article);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProvider(@PathVariable Integer id, @Valid @RequestBody Article article,
													BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new ErrorHandler().inputValidate(bindingResult);
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Article result = this.articleService.updateArticle(article);
			
			return new ResponseEntity<>(result, HttpStatus.OK);			
		}catch(Exception error) {
			 return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteArticle(@PathVariable Integer id){
		try {
			Article result = this.articleService.deleteArticle(id);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/rescue/{id}")
	public ResponseEntity<Object> rescueArticle(@PathVariable Integer id){
		try {
			Article result = this.articleService.rescueArticle(id);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch(Exception error) {
			return new ResponseEntity<>("Error: " + error.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
