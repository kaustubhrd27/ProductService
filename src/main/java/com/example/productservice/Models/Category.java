package com.example.productservice.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BasseModel {
    /*@Id
    private Long id;*/
    //if a category is deleted then, I also should delete associated products
   /* @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;*/
    private String title;
}
