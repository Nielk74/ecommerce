package com.java.ecommerce.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.java.ecommerce.DataInitializer;
import com.java.ecommerce.common.ApiResponse;
import com.java.ecommerce.dto.ProductDto;
import com.java.ecommerce.repositories.CategoryRepository;
import com.java.ecommerce.repositories.ProductRepository;
import com.java.ecommerce.services.ProductService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // Activate the 'test' profile
public class ProductTest {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;


    @BeforeEach
    public void setUp() {
        // data is initialized in DataInitializer.java
    }

    @Test
    public void testAddProductNoCategory() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setPrice(100.0);
        productDto.setCategoryId(99);

        ResponseEntity<ApiResponse> response = productController.addProduct(productDto);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testAddProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setPrice(100.0);
        productDto.setCategoryId(1);

        ResponseEntity<ApiResponse> response = productController.addProduct(productDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getAllProducts() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setPrice(100.0);
        productDto.setCategoryId(1);

        productController.addProduct(productDto);

        ResponseEntity<List<ProductDto>> response = productController.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(7, response.getBody().size());
    }

    @Test
    public void updateProduct() {
        ProductDto newProduct = productService.getProduct(1).get();
        newProduct.setName("new name");
        productController.updateProduct(1, newProduct);
        ProductDto product = productService.getProduct(1).get();
        assertEquals(product.getName(), "new name");
    }


    @Test
    @Rollback
    public void deleteProduct(){
        assertEquals(productController.getAll().getBody().size(), 6);
        assertEquals(productController.deleteProduct(1).getStatusCode(), HttpStatus.OK);
        
        assertEquals(productController.getAll().getBody().size(), 5);
        assertEquals(productService.getProduct(1).isPresent(), false);
    }
}