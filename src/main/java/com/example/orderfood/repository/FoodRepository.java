package com.example.orderfood.repository;

import com.example.orderfood.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long>, JpaSpecificationExecutor<Food> {
//    public Optional<Food> findByName(String name);
    public Page<Food> findAll(Pageable pageable);
    Optional<Food> findByName(String name);

}
