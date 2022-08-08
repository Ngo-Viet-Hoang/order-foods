package com.example.orderfood.repository;

import com.example.orderfood.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
//    public Optional<Food> findByName(String name);
    public Page<Food> findAll(Pageable pageable);

}
