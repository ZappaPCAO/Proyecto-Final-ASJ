package com.bootcampASJ.tzappa.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcampASJ.tzappa.Models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByIsDeletedFalse();
}
