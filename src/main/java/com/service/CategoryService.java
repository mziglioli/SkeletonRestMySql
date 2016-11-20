package com.service;

import org.springframework.stereotype.Service;

import com.model.Category;
import com.repository.CategoryRepository;

@Service
public class CategoryService extends ServiceDefault<Category, CategoryRepository> {

}