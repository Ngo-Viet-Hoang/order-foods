package com.example.orderfood.service;

import com.example.orderfood.entity.Category;
import com.example.orderfood.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
