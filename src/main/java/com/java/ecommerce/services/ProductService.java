package com.java.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.dto.ProductDto;
import com.java.ecommerce.models.Category;
import com.java.ecommerce.models.Product;
import com.java.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> listProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products){
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }

        return productDtos;
    }


    public static ProductDto getDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product=  new Product(productDto,category);
        return product;
    }

    public void addProduct(ProductDto productDto, Category category){
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public Optional<ProductDto> getProduct(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            return Optional.of(getDtoFromProduct(optionalProduct.get()));
        }
        return Optional.empty();
    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }

    public Optional<Product> readProduct(Integer id){
        return productRepository.findById(id);
    }


    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        product.setId(productID);
        productRepository.save(product);
    }

}
