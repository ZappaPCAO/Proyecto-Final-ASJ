package com.bootcampASJ.tzappa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootcampASJ.tzappa.Models.Article;
import com.bootcampASJ.tzappa.Models.Category;
import com.bootcampASJ.tzappa.Models.Provider;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	@Query(value="SELECT a.* FROM Articles a ORDER BY LOWER(a.name)", nativeQuery=true)
    List<Article> findAllOrderByIgnoreCaseName();
	
	List<Article> findByCategory(Category category);
	List<Article> findByProvider(Provider provider);
	List<Article> findByIsDeletedFalse();
	List<Article> findByProviderAndIsDeletedFalse(Provider provider);
}