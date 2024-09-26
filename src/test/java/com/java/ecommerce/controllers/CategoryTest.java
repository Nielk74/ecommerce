package com.java.ecommerce.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.java.ecommerce.common.ApiResponse;
import com.java.ecommerce.dto.ProductDto;
import com.java.ecommerce.models.Category;
import com.java.ecommerce.services.CategoryService;
import com.java.ecommerce.services.ProductService;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // Activate the 'test' profile
public class CategoryTest {

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        // data is initialized in DataInitializer.java
    }

    @Test
    public void testGetAll() {
        assertEquals(categoryController.getAllCategories().getBody().size(), 3);
    }

    @Test
    public void testAddCategory() {
        int size = categoryController.getAllCategories().getBody().size();
        assertEquals(categoryController.createCategory(new Category("test", "test_Desc", "test_url")).getStatusCode(),
                HttpStatus.CREATED);

        assertEquals(categoryService.getCategoryById(size+1).get().getCategoryName(), "test");
    }


    @Test
    public void testUpdateCategory(){
        Category category = categoryService.getCategoryById(1).get();
        category.setCategoryName("new name");
        category.setDescription("new desc");

        assertEquals(categoryController.updateCategory(1,category).getStatusCode(),HttpStatus.OK );

        category = categoryService.getCategoryById(1).get();
        assertEquals(category.getCategoryName(), "new name");
        assertEquals(category.getDescription(), "new desc");
    }


    @Test
    public void deleteCategory(){
        assertEquals(categoryController.deleteCategory(1).getStatusCode(), HttpStatus.OK);
        assertEquals(categoryService.getCategoryById(1).isEmpty(), true);
    }

}