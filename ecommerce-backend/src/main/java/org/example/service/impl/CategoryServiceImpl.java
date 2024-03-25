//package org.example.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.dto.CategoryDto;
//import org.example.entity.Category;
//import org.example.repository.CategoryRepository;
//import org.example.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CategoryServiceImpl implements CategoryService {
//    private final CategoryRepository categoryRepository;
//
//    public CategoryServiceImpl(CategoryRepository categoryRepository, ObjectMapper objectMapper) {
//        this.categoryRepository = categoryRepository;
//        this.objectMapper = objectMapper;
//    }
//    private  final ObjectMapper objectMapper;
//
//    @Override
//    public boolean saveCategory(CategoryDto categoryDto) {
//        Category category=objectMapper.convertValue(categoryDto, Category.class);
//        categoryRepository.save(category);
//    }
//}
