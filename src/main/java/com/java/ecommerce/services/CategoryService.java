package com.java.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.models.Category;
import com.java.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository;

    public void createCategory(Category category) {
        category.setId(null);
        categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(int id){
        return categoryRepository.findById(id);
    }
    
    public void editCategory(int categoryId, Category updateCategory){
        Category category = categoryRepository.getReferenceById(categoryId);
        category.setCategoryName(updateCategory.getCategoryName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());
        categoryRepository.save(category);
    }

	public Category readCategory(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}
    public Optional<Category> readCategory(Integer categoryId) {
		return categoryRepository.findById(categoryId);
	}

    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
