package com.example.productservice.Services;

import com.example.productservice.DTOs.ProductDto;
import com.example.productservice.Exceptions.InvalidProductIdException;
import com.example.productservice.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getProductById(Long id) throws InvalidProductIdException;
    List<Product> getAllProducts();
    Product updateProduct(Long id,Product product) throws InvalidProductIdException;
    Product replaceProduct(Long id, ProductDto productDto);
    Product createProduct(Product product);
    void deleteProduct();
}
