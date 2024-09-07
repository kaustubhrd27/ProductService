package com.example.productservice;

import com.example.productservice.Models.Product;
import com.example.productservice.Projections.ProductWithIdAndTitle;
import com.example.productservice.Repositories.CategoryRepository;
import com.example.productservice.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    void contextLoads() {
    }

    @Test
    public void testQueries() {
        /*List<ProductWithIdAndTitle> products = productRepository.someQuery();

        System.out.println("DEBUG");

        Product p1 = productRepository.doSomethingSql();*/

        categoryRepository.deleteById(252L);
    }
}
