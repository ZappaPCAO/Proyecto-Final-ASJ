package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.IdenticalRecord;
import com.bootcampASJ.tzappa.Models.Article;
import com.bootcampASJ.tzappa.Models.Category;
import com.bootcampASJ.tzappa.Models.Provider;
import com.bootcampASJ.tzappa.Repositories.ArticleRepository;
import com.bootcampASJ.tzappa.Repositories.CategoryRepository;
import com.bootcampASJ.tzappa.Repositories.ProviderRepository;

import jakarta.transaction.Transactional;

@Service
public class ArticleService {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProviderRepository providerRepository;
	
	public List<Article> getArticles() {
		return this.articleRepository.findAll();	
	}
	
	public Optional<Article> getArticleById(Integer id) {
		return this.articleRepository.findById(id);
	}
	
	public Optional<List<Article>> getArticlesByCategory(Integer id) {
		
		Category category = this.categoryRepository.findById(id).get();
		
		return Optional.ofNullable(this.articleRepository.findByCategory(category));
	}
	
	public Optional<List<Article>> getArticlesByProvider(Integer id) {
		
		Provider provider = this.providerRepository.findById(id).get();
		
		return Optional.ofNullable(this.articleRepository.findByProvider(provider));
	}
	
	@Transactional
	public Optional<Article> newArticle(Article article) {	
	    try {
	        return Optional.ofNullable(this.articleRepository.save(article));
	    } catch (DataIntegrityViolationException error) {
	    	System.out.println("Clave duplicada detectada");
            return Optional.empty();
	    }
	}
	
	@Transactional
	public Article updateArticle(Article article){
						
		Article storedArticle = this.articleRepository.findById(article.getId()).get();
		
		if (!article.equals(storedArticle)) {
			article.setUpdatedAt(LocalDateTime.now());
			this.articleRepository.save(article);
			return article; 			
		}
		
		throw new IdenticalRecord("El registro que estás intentando editar es idéntico al existente en la base de datos.");
	}
	
	@Transactional
	public Article deleteArticle(Integer id) {
		
		Article article = this.articleRepository.findById(id).get();
		
		if(article == null)  
			throw new IdenticalRecord("La entidad no fue encontrada en la base de datos");
		if(article.getIsDeleted()) 
			throw new IdenticalRecord("Este registro ya esta eliminado.");
	
		article.setUpdatedAt(LocalDateTime.now());
		article.setIsDeleted(true);
		
		return this.articleRepository.save(article);
	}
	
	@Transactional
	public Article rescueArticle(Integer id) {
		Article article = this.articleRepository.findById(id).get();
		
		if(article == null)  
			throw new IdenticalRecord("La entidad no fue encontrada en la base de datos");
		if(!article.getIsDeleted()) 
			throw new IdenticalRecord("Este registro ya esta salvado.");
	
		article.setUpdatedAt(LocalDateTime.now());
		article.setIsDeleted(false);
		
		return this.articleRepository.save(article);
	}
}
