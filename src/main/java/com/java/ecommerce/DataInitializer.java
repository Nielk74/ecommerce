package com.java.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.java.ecommerce.models.Category;
import com.java.ecommerce.models.Order;
import com.java.ecommerce.models.OrderItem;
import com.java.ecommerce.models.Product;
import com.java.ecommerce.repositories.CategoryRepository;
import com.java.ecommerce.repositories.OrderRepository;
import com.java.ecommerce.repositories.ProductRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public DataInitializer(CategoryRepository categoryRepository, ProductRepository productRepository,
            OrderRepository orderRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert mock data
        categoryRepository
                .save(new Category("Electronics", "Devices and gadgets", "http://example.com/electronics.jpg"));
        categoryRepository.save(new Category("Books", "Books and literature", "http://example.com/books.jpg"));
        categoryRepository.save(new Category("Clothing", "Apparel and accessories", "http://example.com/clothing.jpg"));

        productRepository.save(new Product("Laptop", "http://example.com/laptop.jpg", 1000.0, "A laptop computer",
                categoryRepository.findByCategoryName("Electronics")));
        productRepository.save(new Product("Smartphone", "http://example.com/smartphone.jpg", 500.0, "A smartphone",
                categoryRepository.findByCategoryName("Electronics")));
        // products for Books category
        productRepository.save(new Product("Book", "http://example.com/book.jpg", 10.0, "A book",
                categoryRepository.findByCategoryName("Books")));
        productRepository.save(new Product("E-book", "http://example.com/ebook.jpg", 5.0, "An e-book",
                categoryRepository.findByCategoryName("Books")));
        // products for Clothing category
        productRepository.save(new Product("T-shirt", "http://example.com/tshirt.jpg", 20.0, "A t-shirt",
                categoryRepository.findByCategoryName("Clothing")));
        productRepository.save(new Product("Jeans", "http://example.com/jeans.jpg", 30.0, "A pair of jeans",
                categoryRepository.findByCategoryName("Clothing")));
        Order order1 = new Order();
        order1.setTotal_price(1500.00); // Example total price

        Product product1 = productRepository.findByName("Laptop");
        Product product2 = productRepository.findByName("Smartphone");
        OrderItem item1 = new OrderItem();
        item1.setQuantity(1);
        item1.setPrice(product1.getPrice());
        item1.setProduct(product1); // Set the product reference

        OrderItem item2 = new OrderItem();
        item2.setQuantity(2);
        item2.setPrice(product2.getPrice());
        item2.setProduct(product2); // Set the product reference

        List<OrderItem> orderItems1 = new ArrayList<>();
        orderItems1.add(item1);
        orderItems1.add(item2);
        order1.setOrderItems(orderItems1);

        orderRepository.save(order1);

    }
}