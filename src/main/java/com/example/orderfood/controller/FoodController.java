package com.example.orderfood.controller;

import com.example.orderfood.entity.Category;
import com.example.orderfood.entity.Food;
import com.example.orderfood.entity.entityEnum.FoodStatus;
import com.example.orderfood.repository.CategoryRepository;
import com.example.orderfood.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/foods")
@Slf4j
@RequiredArgsConstructor
public class FoodController {

    Logger logger = Logger.getLogger(FoodController.class.getName());
    final FoodService foodService;
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Food> optionalFood = foodService.findById(id);
        if (!optionalFood.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalFood.get());
    }

    @RequestMapping(path = "/create",method = RequestMethod.POST)
    public ResponseEntity<Food> create(@RequestBody Food food) {
        Optional<Category> category = categoryRepository.findById(food.getCategory().getId());
        if (!category.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(foodService.save(food));
    }

    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "limit", defaultValue = "100") int limit,
                                     Model model) {
        return ResponseEntity.ok(model.addAttribute("Pageable", foodService.findAll(page, limit)));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Food> update(@PathVariable Long id, @RequestBody Food food) {
        Optional<Food> optionalFood = foodService.findById(id);
        if (!optionalFood.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Food existFood = optionalFood.get();
        existFood.setName(food.getName());
        existFood.setImage(food.getImage());
        existFood.setPrice(food.getPrice());
        existFood.setDescription(food.getDescription());
        existFood.setStatus(food.getStatus());
        existFood.setCategory(food.getCategory());
//        existFood.setMealTime(food.getMealTime());
//        existFood.setCreatedBy(food.getCreatedBy());
//        existFood.setUpdatedBy(food.getUpdatedBy());
//        existFood.setDeletedBy(food.getDeletedBy());
//        existFood.setDeletedAt(LocalDateTime.now());
//        existFood.setUpdatedAt(LocalDateTime.now());
        foodService.save(existFood);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "delete/{id}")
    public  ResponseEntity<Food> delete(@PathVariable Long id ) {
        logger.info("delete" + id);
        Optional<Food> optionalFood = foodService.findById(id);
        if (!optionalFood.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Food existFood = optionalFood.get();
        // map object
//        existFood.getName();
        existFood.setStatus(FoodStatus.STOP);
//        existFood.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(foodService.save(existFood));
    }
//    @DeleteMapping( path = "{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        logger.info("delete" + id);
//        if (!foodService.findById(id).isPresent()) {
//            ResponseEntity.badRequest().build();
//        }
//        foodService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
//    @PostMapping("/uploadImage")
//    public String uploadImage(@RequestParam("image") MultipartFile image){
//        String returnValue = "";
//        try {
//            foodService.saveImage(image);
//        }catch (Exception e){
//            e.printStackTrace();
//            log.error("Error saving photo",e);
//            returnValue = "error";
//        }
//
//        return returnValue;
//    }

}
