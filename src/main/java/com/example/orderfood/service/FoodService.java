package com.example.orderfood.service;

import com.example.orderfood.entity.Food;
import com.example.orderfood.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    final FoodRepository foodRepository;
    public Food save(Food food) {
        return foodRepository.save(food);
    }
    public List<Food> findAll() {
        return foodRepository.findAll();
    }
    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    }
    public void deleteById(Long id) {
        foodRepository.deleteById(id);
    }

    public void saveImage(MultipartFile image) throws Exception{
        String folder = "/photos/";
        byte[] bytes =  image.getBytes();
        Path path = Paths.get(folder + image.getOriginalFilename());
        Files.write(path,bytes);
    }

}
