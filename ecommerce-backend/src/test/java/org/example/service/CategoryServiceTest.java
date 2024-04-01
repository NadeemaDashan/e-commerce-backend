package org.example.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CategoryDto;
import org.example.entity.Category;
import org.example.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
            @Autowired
    CategoryService categoryService;

    @BeforeEach
    public void setUp() {
       MockitoAnnotations.initMocks(this);
    }

    @Test
    public void CategoryService_SaveCategory_ReturnObject(){
        //Given
        Category category = Category.builder().id(null).name("Mens").build();
        CategoryDto categoryDto = CategoryDto.builder().id(null).name("Mens").build();
        //When
        when(categoryRepository.save((Category) any())).thenReturn(category);
        when(objectMapper.convertValue((Object) any(), (JavaType) any())).thenReturn(category);
        boolean isSaved=categoryService.saveCategory(categoryDto);
        //Then
        Assertions.assertTrue(isSaved);
    }

}
