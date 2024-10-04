package com.example.productservice.Services;

import com.example.productservice.DTOs.FakeProductDto;
import com.example.productservice.DTOs.ProductDto;
import com.example.productservice.Exceptions.InvalidProductIdException;
import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    private RedisTemplate redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    private Product convertFakeProductDtoToProduct(FakeProductDto fakeProductDto) {
        Product product = new Product();
        product.setId(fakeProductDto.getId());
        product.setDescription(fakeProductDto.getDescription());
        product.setTitle(fakeProductDto.getTitle());
        product.setPrice(fakeProductDto.getPrice());
        product.setImage(fakeProductDto.getImage());
        //Category category = new Category();
        //category.setName(fakeProductDto.getCategory());
        //product.setCategory(category);

        return product;
    }

    private FakeProductDto convertProductDtoToFakeProductDto(ProductDto productDto) {
        FakeProductDto fakeProductDto = new FakeProductDto();
        fakeProductDto.setId(productDto.getId());
        fakeProductDto.setDescription(productDto.getDescription());
        fakeProductDto.setTitle(productDto.getTitle());
        fakeProductDto.setPrice(productDto.getPrice());
        fakeProductDto.setImage(productDto.getImage());
        fakeProductDto.setCategory(productDto.getCategory());
        return fakeProductDto;
    }
    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        //Here we need to call FakeStore API to get the product with given ID
        Product product =(Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCT_" + id);
        // This retrieves a product from the Redis cache with the key "PRODUCTS" (hash key), and the field "PRODUCT_" + id
        // Redis stores data in a hash structure where "PRODUCTS" is a hash key, and "PRODUCT_" + id

        if (product != null) {
            // cache hit
            return product;
        }
        // Cache Miss: If the product is not found in the Redis cache (i.e., product == null),
        // it calls the external FakeStore API to fetch the product detail
        FakeProductDto fakeProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProductDto.class);

        if (fakeProductDto == null) {
            throw new InvalidProductIdException(id,"Invalid product id");
        }

        // Now we should convert fakeProductDto to Product
        product = convertFakeProductDtoToProduct(fakeProductDto);
        redisTemplate.opsForHash().put("PRODUCTS","PRODUCT_" + id, product);
        //This stores the product in the Redis cache using the "PRODUCTS" hash key and the "PRODUCT_" + id field.

        return product;

        /*int a = 1 / 0;
        return null;*/
       /* throw new RuntimeException("Not implemented");*/
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortDir) {
        FakeProductDto[] fakeProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeProductDto[].class);
        //int getForObject method we need response type
        List<Product> products = new ArrayList<>();

        for (FakeProductDto fakeProductDto : fakeProductDtos) {
            products.add(convertFakeProductDtoToProduct(fakeProductDto));
        }


        return new PageImpl<>(products);
    }

    @Override
    public Product updateProduct(Long id, Product product) {return null;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        System.out.println("Hey Guys..............");
        // We can replace product using put method
        // replace the product with given id with the given product

        FakeProductDto fakeProductDto = convertProductDtoToFakeProductDto(productDto);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeProductDto, FakeProductDto.class);
        HttpMessageConverterExtractor<FakeProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeProductDto.class, restTemplate.getMessageConverters());
        FakeProductDto updatedFakeProductDto = restTemplate.execute("https://fakestoreapi.com/products/"+ id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeProductDtoToProduct(fakeProductDto);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct() {

    }
}
