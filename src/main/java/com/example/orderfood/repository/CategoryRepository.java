package com.example.orderfood.repository;

import com.example.orderfood.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
//    public Optional<Category> findByName(String name);
    public Page<Category> findAll(Pageable pageable);

}
