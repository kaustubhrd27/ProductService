package com.example.productservice.Repositories;


import com.example.productservice.Models.Category;
import com.example.productservice.Models.Product;
import com.example.productservice.Projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //#Read Queries
    //this long is the data type of the primary key
    public Optional<Product> findById(long id);
    public Optional<Product> findByTitleAndDescription(String title, String description);
    public List<Product> findByTitleContaining(String word);
    //this might use like keywords
    public Optional<Product> findTopThreeByTitle(String title);
    public Optional<Product> findByCategory(Category category);


    //#DeleteQueries
    void deleteById(long id);
    void deleteByTitle(String title);

    // Create and Update

    Product  save(Product product);


    //HQL Queries

    @Query("select p.id as id,p.title as title from Product p where p.price > 100000 and p.title like '%pro%'")
    List<ProductWithIdAndTitle> someQuery();

    @Query(value = "select * from product p where p.id = 2", nativeQuery = true)
    Product doSomethingSql();

}


/*
*
* ProductRepository -----> select* from product where id = ?
* List<Product findALl() ------> select * from product
*
*but jpa does all these work on our behalf
*
*for Create and Update ==> save()
* Product save()
*
*
*
*
*
*
*
*
* */