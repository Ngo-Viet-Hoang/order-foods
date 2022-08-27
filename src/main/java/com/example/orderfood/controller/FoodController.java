package com.example.orderfood.controller;

import com.example.orderfood.entity.*;
import com.example.orderfood.entity.entityEnum.FoodStatus;
import com.example.orderfood.repository.CategoryRepository;
import com.example.orderfood.repository.FoodRepository;
import com.example.orderfood.service.CategoryService;
import com.example.orderfood.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
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
    @Autowired
    CategoryService categoryService;
    @Autowired
    FoodRepository foodRepository;

//    @RequestMapping(method = RequestMethod.GET,path = "category")
//    public ResponseEntity<?> getByAccount(){
//        Optional<Category> category = categoryService.findById();
//        List<Food> foods = foodRepository.findByCategoryId(category.get().getId());
//
//        return ResponseEntity.ok(foods);
//    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<ResponseData> getDetail(@PathVariable Long id) {
        Optional<Food> optionalFood = foodService.findById(id);
        if (!optionalFood.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        ResponseData responseData = new ResponseData("Success",200,optionalFood.get());
        return ResponseEntity.ok(responseData);
    }

    @RequestMapping(path = "/create",method = RequestMethod.POST)
    public ResponseEntity<ResponseData> create(@RequestBody Food food) {
        Optional<Category> category = categoryRepository.findById(food.getCategory().getId());
        if (!category.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        if (food.getName() != null && food.getName() != ""){
            food.setName(food.getName());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Food Name is not null");
        }
        if (food.getImage() != null && food.getImage() != ""){
           food.setImage(food.getImage());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Image is not null");
        }

        if (food.getPrice() != null){
            food.setPrice(food.getPrice());

        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Price is not null");
        }

        if (food.getDescription() != null)
            food.setDescription(food.getDescription());
        if (food.getStatus() != null){
            food.setStatus(food.getStatus());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Status is not null");
        }
        if (food.getCategory() != null){
            food.setCategory(food.getCategory());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category is not null");
        }
        ResponseData responseData = new ResponseData("Success",200,foodService.save(food));

        return ResponseEntity.ok(responseData);
    }

//    @RequestMapping(path = "/list",method = RequestMethod.GET)
//    public ResponseEntity<List<Food>> getList(){
//        return ResponseEntity.ok(foodService.findAll());
//    }

    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public ResponseEntity<ResponseData> findAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "limit", defaultValue = "100") int limit,
                                     Model model) {
        ResponseData responseData = new ResponseData("Success",200,model.addAttribute("Pageable", foodService.findAll(page, limit)));
        return ResponseEntity.ok(responseData);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<ResponseData> update(@PathVariable Long id, @RequestBody Food food) {
        Optional<Food> optionalFood = foodService.findById(id);

        logger.info("abc: " + id);
        if (!optionalFood.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Food existFood = optionalFood.get();
        if (food.getName() != null && food.getName() != ""){
            existFood.setName(food.getName());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Food Name is not null");
        }
        if (food.getImage() != null && food.getImage() != ""){
            existFood.setImage(food.getImage());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Image is not null");
        }

        if (food.getPrice() != null){
            existFood.setPrice(food.getPrice());

        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Price is not null");
        }

        if (food.getDescription() != null)
        existFood.setDescription(food.getDescription());
        if (food.getStatus() != null){
            existFood.setStatus(food.getStatus());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Status is not null");
        }
        if (food.getCategory() != null)
            existFood.setCategory(food.getCategory());
        foodService.save(existFood);
        ResponseData responseData = new ResponseData("Success",200,existFood);
        return ResponseEntity.ok(responseData);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "delete/{id}")
    public  ResponseEntity<ResponseData> delete(@PathVariable Long id ) {
        logger.info("delete" + id);
        Optional<Food> optionalFood = foodService.findById(id);
        if (!optionalFood.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Food existFood = optionalFood.get();
        existFood.setStatus(FoodStatus.STOP);
        ResponseData responseData = new ResponseData("Success",200,foodService.save(existFood));
        return ResponseEntity.ok(responseData);
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


}
