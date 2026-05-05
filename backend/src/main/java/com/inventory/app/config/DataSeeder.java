package com.inventory.app.config;

import com.inventory.app.entity.Category;
import com.inventory.app.entity.Product;
import com.inventory.app.repository.CategoryRepository;
import com.inventory.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            log.info("Seeding sample data...");

            Category electronics = categoryRepository.save(Category.builder().name("Electronics").build());
            Category clothing = categoryRepository.save(Category.builder().name("Clothing").build());
            Category food = categoryRepository.save(Category.builder().name("Food & Beverages").build());
            Category books = categoryRepository.save(Category.builder().name("Books").build());

            productRepository.save(Product.builder().name("Laptop Pro 15").price(1299.99).quantity(25).category(electronics).build());
            productRepository.save(Product.builder().name("Wireless Headphones").price(149.99).quantity(80).category(electronics).build());
            productRepository.save(Product.builder().name("Smartphone X12").price(899.00).quantity(45).category(electronics).build());
            productRepository.save(Product.builder().name("USB-C Hub").price(49.99).quantity(120).category(electronics).build());
            productRepository.save(Product.builder().name("Men's Cotton T-Shirt").price(24.99).quantity(200).category(clothing).build());
            productRepository.save(Product.builder().name("Women's Denim Jacket").price(79.99).quantity(60).category(clothing).build());
            productRepository.save(Product.builder().name("Running Shoes").price(119.99).quantity(35).category(clothing).build());
            productRepository.save(Product.builder().name("Organic Coffee Beans (1kg)").price(18.99).quantity(150).category(food).build());
            productRepository.save(Product.builder().name("Green Tea Collection").price(12.49).quantity(200).category(food).build());
            productRepository.save(Product.builder().name("Clean Code").price(34.99).quantity(40).category(books).build());
            productRepository.save(Product.builder().name("Design Patterns").price(44.99).quantity(30).category(books).build());

            log.info("Sample data seeded successfully.");
        }
    }
}
