package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Category;
import com.model.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	public User findByName(String name);

}