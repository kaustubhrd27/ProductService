package com.example.productservice.integration.controller;

import com.example.productservice.Controllers.ProductControllers;
import com.example.productservice.Models.Product;
import com.example.productservice.Repositories.ProductRepository;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc

public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // For simulating HTTP requests

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        // Clean the repository before each test
        productRepository.deleteAll();

        // Set up a sample product for testing
        Product product = new Product();
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        productRepository.save(product);
    }

    @Test
    public void testGetProductById() throws Exception {
        // Retrieve the product we just saved
        Product product = productRepository.findAll().get(0);

        // Perform a GET request and verify the response

        mockMvc.perform(get("/products/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Product"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    public void testCreateProduct() throws Exception {
        // Create a new product using POST
        String newProductJson = "{\"title\":\"New Product\",\"description\":\"New Description\"}";

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newProductJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Product"))
                .andExpect(jsonPath("$.description").value("New Description"));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Get all products
        mockMvc.perform(get("/products/all")
                        .param("pageNumber", "0")
                        .param("pageSize", "10")
                        .param("sortDir", "asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}
