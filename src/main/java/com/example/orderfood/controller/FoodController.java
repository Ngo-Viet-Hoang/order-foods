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

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/v1/foods")
@Slf4j
@RequiredArgsConstructor
public class FoodController {
    final FoodService foodService;
    @Autowired
    CategoryRepository categoryRepository;

    //    @RequestMapping(path = "/create", method = RequestMethod.GET)
//    public String createFood(Model model){
//        model.addAttribute("food",new Food());
//        return "view/foods/create";
//    }
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable String id) {
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
//    @RequestMapping(path = "/create", method = RequestMethod.POST)
//    public String processSaveFood(@Valid @ModelAttribute("food") Food food,
//                                  BindingResult bindingResult,
//                                  Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("food", food);
//            return "view/foods/create";
//        }
//        foodService.save(food);
//        return "redirect:/admin/foods/create";
//    }

    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit,
                                     Model model) {
        return ResponseEntity.ok(model.addAttribute("Pageable", foodService.findAll(page, limit)));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Food> update(@PathVariable String id, @RequestBody Food food) {
        Optional<Food> optionalFood = foodService.findById(id);
        if (!optionalFood.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Food existFood = optionalFood.get();
        existFood.setName(food.getName());
        existFood.setStatus(FoodStatus.SALE);
        existFood.setCreatedBy(food.getCreatedBy());
        existFood.setUpdatedBy(food.getUpdatedBy());
        existFood.setDeletedBy(food.getDeletedBy());
        existFood.setDeletedAt(LocalDateTime.now());
        existFood.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(foodService.save(existFood));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "delete/{id}")
    public  ResponseEntity<Food> delete(@PathVariable String id, @RequestBody Food food) {
        Optional<Food> optionalFood = foodService.findById(id);
        if (!optionalFood.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Food existFood = optionalFood.get();
        // map object
        existFood.setName(food.getName());
        existFood.setStatus(FoodStatus.STOP);
        existFood.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(foodService.save(existFood));
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!foodService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        foodService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
