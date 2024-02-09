package com.bootcampASJ.tzappa.Services;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.ExceptionCustom;
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
		return this.articleRepository.findAllByOrderByName();
	}
	
	public List<Article> getArticlesByActive(){
		return this.articleRepository.findByIsDeletedFalse();
	}
	
	public Article getArticleById(Integer id) {
		return this.articleRepository.findById(id).get();
	}
	
	public List<Article> getArticlesByCategory(Integer id) {		
		Category category = this.categoryRepository.findById(id).get();
		
		return this.articleRepository.findByCategory(category);
	}
	
	public List<Article> getArticlesByProvider(Integer id) {		
		Provider provider = this.providerRepository.findById(id).get();
		
		return this.articleRepository.findByProvider(provider);
	}
	
	public List<Article> getArticlesActivesByProvider(Integer id) {		
		Provider provider = this.providerRepository.findById(id).get();
		
		return this.articleRepository.findByProviderAndIsDeletedFalse(provider);
	}
	
	@Transactional
	public Article newArticle(Article article) {	
		List<Article> storedArticles = this.articleRepository.findAll();
		
		for(Article art : storedArticles) {
			if( (article.getCodArticle().toLowerCase()).equals(art.getCodArticle().toLowerCase()) )
				throw new ExceptionCustom("Ya hay un registro asociado a ese SKU.");			
		}
		
		return this.articleRepository.save(article);
	}
	
	@Transactional
	public Article updateArticle(Article article){
		List<Article> storedArticles = this.articleRepository.findAll();
		
		if( article.equals(this.articleRepository.findById(article.getId()).get()) )
			throw new ExceptionCustom("El registro que estás intentando editar es idéntico "
					+ "al existente en la base de datos.");
		
		for(Article arti : storedArticles) {
			if(article.getId() != arti.getId()) { // Si no es el mismo me fijo.
				if( (article.getCodArticle().toLowerCase()).equals(arti.getCodArticle().toLowerCase()) )
					throw new ExceptionCustom("Ya hay un registro asociado a ese SKU.");				
			}
		}
		article.setUpdatedAt(LocalDateTime.now());
		
		return this.articleRepository.save(article);
	}
	
	@Transactional
	public Article deleteArticle(Integer id) {
		
		Article article = this.articleRepository.findById(id).get();
		
		if(article == null)  
			throw new ExceptionCustom("Este registro no fue encontrado en la base de datos.");
		if(article.getIsDeleted()) 
			throw new ExceptionCustom("Este registro ya esta eliminado.");
	
		article.setUpdatedAt(LocalDateTime.now());
		article.setIsDeleted(true);
		
		return this.articleRepository.save(article);
	}
	
	@Transactional
	public Article rescueArticle(Integer id) {
		Article article = this.articleRepository.findById(id).get();
		
		if(article == null)  
			throw new ExceptionCustom("Este registro no fue encontrado en la base de datos.");
		if(!article.getIsDeleted()) 
			throw new ExceptionCustom("Este registro ya esta salvado.");
	
		article.setUpdatedAt(LocalDateTime.now());
		article.setIsDeleted(false);
		
		return this.articleRepository.save(article);
	}
}
