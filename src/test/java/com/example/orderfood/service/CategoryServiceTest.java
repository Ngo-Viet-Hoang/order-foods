package com.example.orderfood.service;

import com.example.orderfood.OrderFoodApplication;
import com.example.orderfood.entity.Category;
import com.example.orderfood.entity.entityEnum.CategoryStatus;
import com.example.orderfood.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@Component
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderFoodApplication.class)
class CategoryServiceTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void saveCategogy() {
        Category category1 = Category.builder()
                .name("Category1")
                .categoryStatus(CategoryStatus.UNSALE)
                .build();
        Category category2 = Category.builder()
                .name("Category3")
                .categoryStatus(CategoryStatus.SALE)
                .build();
        categoryRepository.save(category1);
        categoryRepository.save(category2);
    }
}