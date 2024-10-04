package com.example.productservice.Controllers;

import com.example.productservice.Exceptions.InvalidProductIdException;
import com.example.productservice.Models.Product;
import com.example.productservice.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@SpringBootTest
class ProductControllersTest {

//
//    @Autowired
////    private ProductControllers productControllers;
////  @MockBean
//    private ProductService productService;
//    @Test
//    void testGetProductByIdValidCase() throws InvalidProductIdException {
//        /*Product product = productControllers.getProductById(10L);*/
//
//        // 3A's
//        Product product = new Product();
//        product.setId(10L);
//        product.setTitle("IPhone");
//        product.setPrice(100000);
//
//        when(productService.getProductById(10L)).thenReturn(product);///Mocking ProductService
//
//        ResponseEntity<Product> expectedResponse = productControllers.getProductById(10L);
//        assertEquals(product, expectedResponse.getBody());
//
//        assertEquals(HttpStatus.OK, expectedResponse.getStatusCode());
//
//    }

//    @Test
//    void testGetProductByIdInvalidCase() throws InvalidProductIdException {
//        when(productService.getProductById(1000L))
//                .thenThrow(new InvalidProductIdException(1000L, "Invalid Product Id"));
//
//        assertThrows(
//                InvalidProductIdException.class,
//                () -> productControllers.getProductById(1000L)
//        );
//    }

//    @Test
//    void testGetAllProducts() {
//        List<Product> products = new ArrayList<>();
//
//        Product p1 = new Product();
//        p1.setId(1L);
//        p1.setTitle("iPhone 15");
//
//        Product p2 = new Product();
//        p2.setId(2L);
//        p2.setTitle("iPhone 15 pro");
//
//        Product p3 = new Product();
//        p3.setId(3L);
//        p3.setTitle("iPhone 15 pro max");
//
//        products.add(p1);
//        products.add(p2);
//        products.add(p3);
//        when(productService.getAllProducts()).thenReturn(products);

        /*assertEquals(products, productControllers.getAllProducts(t));*/

    //}
}