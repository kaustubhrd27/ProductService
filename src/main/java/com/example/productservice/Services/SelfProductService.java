package com.example.productservice.Services;

import com.example.productservice.DTOs.ProductDto;
import com.example.productservice.Exceptions.InvalidProductIdException;
import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import com.example.productservice.Repositories.CategoryRepository;
import com.example.productservice.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        // we will be fetching product for given id from our DB
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new InvalidProductIdException(id,"Invalid product id");
        }
        return optionalProduct.get();

    }

    @Override
    public List<Product> getAllProducts() {
        // here we will bw fetching all the products from our DB
        return List.of();
    }

    @Override
    public Product updateProduct(Long id,Product product) throws InvalidProductIdException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new InvalidProductIdException(id,"Invalid product id");
        }


        Product currentProduct = optionalProduct.get();

        if (product.getTitle() != null) {
            // if title is not null in  the given product it means we want to update
            // title in DB for product with given id
            currentProduct.setTitle(product.getTitle());
        }

        if (product.getDescription() != null) {
            // if title is not null in  the given product it means we want to update
            // title in DB for product with given id
            currentProduct.setDescription(product.getDescription());
        }

        if (product.getCategory() != null) {
            currentProduct.setCategory(product.getCategory());
        }

        if (product.getImage() != null) {
            currentProduct.setImage(product.getImage());
        }

        if (product.getPrice() != 0) {
            currentProduct.setPrice(product.getPrice());
        }

        return productRepository.save(currentProduct);
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();

        /*if (category.getId() == null) {
            // first save this category in the DB
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }*/
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct() {

    }
}
