package org.example.repository;

import org.example.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Iterator;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Test
    public void CategoryRepository_SaveCategory_ReturnCategory(){
        //Given
        Category category = Category.builder().id(1L).name("Mens").build();
        //When
        Category savedCategory=categoryRepository.save(category);
        //Then
        Assertions.assertNotNull(savedCategory);
        Assertions.assertEquals(category.getName(),savedCategory.getName());
    }

    @Test
    public void CategoryRepository_GetCategoryByName_ReturnCategory(){
        //Given
        Category category = Category.builder().id(null).name("Mens").build();
        //When
        Category savedCategory=categoryRepository.save(category);
        Category categoryByUsername=categoryRepository.getByName(savedCategory.getName());
        //Then
        System.out.println(categoryByUsername);

        Assertions.assertEquals(category.getName(),categoryByUsername.getName());
    }

    @Test
    public void CategoryRepository_GetAllCategory_ReturnList(){
        //Given
        Category category = Category.builder().id(null).name("Mens").build();
        Category category2 = Category.builder().id(null).name("Women").build();
        ArrayList<Category> list = new ArrayList<>();

        //When
         categoryRepository.save(category);
         categoryRepository.save(category2);
         Iterable<Category> iterable=categoryRepository.findAll();
        Iterator<Category> iterator=iterable.iterator();
        while(iterator.hasNext()){
            Category savedCategory=iterator.next();
            list.add(savedCategory);
        }
        //Then
        Assertions.assertEquals(list.size(),2);
    }

    @Test
    public void CategoryRepository_DeleteCategory_ReturnBoolean(){
        //Given
        Category category = Category.builder().id(null).name("Mens").build();
        ArrayList<Category> list = new ArrayList<>();
        //When
        Category savedCategory=categoryRepository.save(category);
        categoryRepository.deleteById(savedCategory.getId());
        Iterable<Category> iterable=categoryRepository.findAll();
        Iterator<Category> iterator=iterable.iterator();
        while(iterator.hasNext()){
            Category category1=iterator.next();
            list.add(category1);
        }
        //Then
        for(Category category1:list){
            Assertions.assertNotEquals(category1.getName(),category.getName());
        }
    }
}
