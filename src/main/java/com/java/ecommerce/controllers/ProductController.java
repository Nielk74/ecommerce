package com.java.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.java.ecommerce.dto.ProductDto;
import com.java.ecommerce.models.Category;
import com.java.ecommerce.services.CategoryService;
import com.java.ecommerce.services.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new product", notes = "Provide product details to add a new product")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "category id " + productDto.getCategoryId() + " invalid"),
                    HttpStatus.CONFLICT);
        }

        productService.addProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "Product created: " + productDto.getName()),
                HttpStatus.CREATED);
    }

    @PutMapping("update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID,
            @RequestBody @Valid ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productID, productDto, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("delete/{productID}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productID") Integer productID) {
        Optional<ProductDto> optionalProduct = productService.getProduct(productID);
        if (!optionalProduct.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Product doesn't exist"),
                    HttpStatus.CONFLICT);
        }
        productService.deleteProduct(productID);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been deleted"), HttpStatus.OK);
    }

}
