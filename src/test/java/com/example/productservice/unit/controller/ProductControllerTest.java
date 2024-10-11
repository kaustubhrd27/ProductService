package com.example.productservice.unit.controller;

import com.example.productservice.Controllers.ProductControllers;
import com.example.productservice.Models.Product;
import com.example.productservice.Services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

//@SpringBootTest

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductControllers productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById() throws Exception {
        // Mocking the ProductService
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");

        when(productService.getProductById(1L)).thenReturn(product);

        // Test the controller method
        ResponseEntity<Product> response = productController.getProductById(1L);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setTitle("New Product");

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        Product createdProduct = productController.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("New Product", createdProduct.getTitle());

        verify(productService, times(1)).createProduct(product);
    }

    // Other tests like updateProduct, deleteProduct can follow similar patterns
}
