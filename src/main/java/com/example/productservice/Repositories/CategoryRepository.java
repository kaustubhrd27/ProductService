package com.example.productservice.Repositories;

import com.example.productservice.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);

    void deleteById(Long id);
}
