package com.example.productservice.Controllers;

import com.example.productservice.DTOs.InvalidProductIdResponseDto;
import com.example.productservice.DTOs.ProductDto;
import com.example.productservice.DTOs.Role;
import com.example.productservice.DTOs.UserDto;
import com.example.productservice.Exceptions.InvalidProductIdException;
import com.example.productservice.Models.Product;
import com.example.productservice.Services.ProductService;
import com.example.productservice.commons.AunthenticationCommons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductControllers {

    @Autowired
    private AunthenticationCommons aunthenticationCommons;

    private ProductService productService;
//    private final PagedResourcesAssembler<Product> pagedResourcesAssembler;
    private RestTemplate restTemplate;

    public ProductControllers(@Qualifier("fakeStoreProductService") ProductService productService,
                              RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {
        //Here we need to call FakeStore API to get the product with given ID
        /*Product product = null;
        try {
             product = productService.getProductById(id);
        } catch (RuntimeException e) {
            System.out.println("Something went wrong");
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }*/
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
//                "http://UserService/users/10",String.class);


        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
        /* throw new RuntimeException("Something Went wrong");*/
        /*int a = 1 / 0;
        return null;

        this was written just to check the arithmetic exception
        which is present in controller advice for the central use in our application. //
        */
    }

//    @GetMapping("/all")
//    public ResponseEntity<PagedModel<Product>> getAllProducts(
//            @RequestParam(defaultValue = "0") int pageNumber,
//            @RequestParam(defaultValue = "10") int pageSize,
//            @RequestParam(defaultValue = "asc") String sortDir) {
//        System.out.println("Gayatri");
//        Page<Product> products = productService.getAllProducts(pageNumber, pageSize, sortDir);
//
//        // Create PagedModel directly from Page<Product>
//        PagedModel<Product> pagedModel = PagedModel.of(products.getContent(), new PagedModel.PageMetadata(
//                products.getSize(),
//                products.getNumber(),
//                products.getTotalElements(),
//                products.getTotalPages()
//        ));
//
//        return ResponseEntity.ok(pagedModel);
//    }


//    @GetMapping("/all/{token}")
    @GetMapping("/all")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam("pageNumber") int pageNumber,
                                                        @RequestParam("pageSize") int pageSize,
                                                        @RequestParam("sortDir") String sortDir) {
//        UserDto userDto = aunthenticationCommons.validateToken(token);
//
//        if(userDto == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

       /* boolean isAdmin = false;
        for (Role role : userDto.getRoles()) {
            if (role.equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if(!isAdmin) {
            return null;
        }*/
        System.out.println("Gayu");
        Page<Product> products = productService.getAllProducts(pageNumber, pageSize, sortDir);
        return new ResponseEntity<>(products, HttpStatus.OK);
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