package com.example.orderfood.controller;

import com.example.orderfood.entity.Category;
import com.example.orderfood.entity.ResponseData;
import com.example.orderfood.entity.entityEnum.CategoryStatus;
import com.example.orderfood.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/categories")
@Slf4j
public class CategoryController {
    final CategoryService categoryService;
//    @RequestMapping(path = "/create", method = RequestMethod.GET)
//    public String createCategory(Model model){
//        model.addAttribute("food",new Category());
//        return "view/categories/create";
//    }
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseData> processSaveCategory(@RequestBody Category category){
        if(category.getName() != null && category.getName() != ""){
            category.setName(category.getName());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name is not null");
        }
        if(category.getCategoryStatus() != null){
            category.setCategoryStatus(category.getCategoryStatus());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Status is not null");
        }
        ResponseData responseData = new ResponseData("Success",200,categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }
    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<ResponseData> getDetail(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        ResponseData responseData = new ResponseData("Success",200,optionalCategory.get());
        return ResponseEntity.ok(responseData);
    }
    @RequestMapping(path = "list" , method = RequestMethod.GET)
    public ResponseEntity<ResponseData>  getList(){
        ResponseData responseData = new ResponseData("Success",200,categoryService.findAll());
        return ResponseEntity.ok(responseData);
    }
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<ResponseData> update(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Category existCategory = optionalCategory.get();
        if(category.getName() != null && category.getName() != ""){
            existCategory.setName(category.getName());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name is not null");
        }
        if(category.getCategoryStatus() != null){
            existCategory.setCategoryStatus(category.getCategoryStatus());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Status is not null");
        }
        ResponseData responseData = new ResponseData("Success",200,categoryService.save(existCategory));
        return ResponseEntity.ok(responseData);
    }
    @RequestMapping(method = RequestMethod.PUT, path = "/delete/{id}")
    public ResponseEntity<ResponseData> delete(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Category existCategory = optionalCategory.get();
        existCategory.setCategoryStatus(CategoryStatus.STOP);
        ResponseData responseData = new ResponseData("Success",200,categoryService.save(existCategory));
        return ResponseEntity.ok(responseData);
    }

//    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        if (!categoryService.findById(id).isPresent()) {
//            ResponseEntity.badRequest().build();
//        }
//        categoryService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
}
