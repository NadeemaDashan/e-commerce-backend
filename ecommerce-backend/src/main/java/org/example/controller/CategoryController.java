//package org.example.controller;
//
//import org.example.dto.CategoryDto;
//import org.example.service.CategoryService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/category")
//public class CategoryController {
//
//    private final CategoryService categoryService;
//
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @PostMapping("/add")
//    public boolean addCategory(@RequestBody CategoryDto categoryDto){
//        return categoryService.saveCategory(categoryDto);
//
//    }
//
//    @GetMapping("/getAll")
//    public List<CategoryDto> getAllCategories(){
//
//    }
//
//    @GetMapping("/get{name}")
//    public CategoryDto getCategoryByName(@PathVariable String name){
//
//    }
//}
