package com.java.ecommerce.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.ecommerce.common.ApiResponse;
import com.java.ecommerce.models.Category;
import com.java.ecommerce.services.CategoryService;
import com.java.ecommerce.utils.Helper;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> body = categoryService.getCategories();
		return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
		if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"),
					HttpStatus.CONFLICT);
		}

		categoryService.createCategory(category);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "created the category " + category.getCategoryName()), HttpStatus.CREATED);
	}

	@PutMapping("update/{categoryID}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") int categoryId,
			@RequestBody Category category) {
		if (!Helper.notNull(categoryService.readCategory(categoryId))) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category doesn't exist"),
					HttpStatus.CONFLICT);
		}

		categoryService.editCategory(categoryId, category);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "update the category " + category.getCategoryName()), HttpStatus.OK);

	}


	@DeleteMapping("delete/{categoryID}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryID") int categoryId) {
		if (!Helper.notNull(categoryService.readCategory(categoryId))) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category doesn't exist"),
					HttpStatus.CONFLICT);
		}

		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "deleted the category " + categoryId), HttpStatus.OK);
	}

}