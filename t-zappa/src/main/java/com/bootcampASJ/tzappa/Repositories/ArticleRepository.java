package com.bootcampASJ.tzappa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.Article;
import com.bootcampASJ.tzappa.Models.Category;
import com.bootcampASJ.tzappa.Models.Provider;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	List<Article> findByCategory(Category category);
	List<Article> findByProvider(Provider provider);
}
