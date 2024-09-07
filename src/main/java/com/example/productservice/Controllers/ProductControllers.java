package com.example.productservice.Controllers;

import com.example.productservice.DTOs.InvalidProductIdResponseDto;
import com.example.productservice.DTOs.ProductDto;
import com.example.productservice.Exceptions.InvalidProductIdException;
import com.example.productservice.Models.Product;
import com.example.productservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductControllers {
    private ProductService productService;


    public ProductControllers(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException{
        //Here we need to call FakeStore API to get the product with given ID
        /*Product product = null;
        try {
             product = productService.getProductById(id);
        } catch (RuntimeException e) {
            System.out.println("Something went wrong");
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }*/
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
       /* throw new RuntimeException("Something Went wrong");*/
        /*int a = 1 / 0;
        return null;

        this was written just to check the arithmetic exception
        which is present in controller advice for the central use in our application. //
        */
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product) throws InvalidProductIdException {
        return productService.updateProduct(id,product);
    }


    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, ProductDto productDto) {
        return productService.replaceProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {}
}

/*
* {
    "title" : "Iphone",
    "price" : "149000",
    "category" : {
        "title" : "Iphone"
    },
    "description" : "Best Iphone Ever",
    "image" :"https://i.pravatar.cc"

}*/