package org.example.repository;

import org.example.entity.SubCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.mockito.BDDMockito.then;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SubCategoryRepositoryTest {
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Test
    public void SubCategoryRepository_SaveSubCategory_ReturnSubCategoryObject(){
        //Given
        SubCategory subCategory = SubCategory.builder().id(null).name("Womens").build();
        //When
        SubCategory savedSubCategory=subCategoryRepository.save(subCategory);
        //Then
        Assertions.assertEquals(subCategory,savedSubCategory);
    }

    @Test
    public void SubCategoryRepository_UpdateSubCategory_ReturnSubCategoryObject(){
        //Given
        SubCategory subCategory = SubCategory.builder().id(null).name("Womens").build();
        //When
        SubCategory savedSubCategory=subCategoryRepository.save(subCategory);
        savedSubCategory.setName("Mens");
        //Then
        Assertions.assertEquals(savedSubCategory.getName(),"Mens");
    }

    @Test
    public void SubCategoryRepository_DeleteSubCategory_ReturnVoid() throws Exception{
        //Given
        SubCategory subCategory = SubCategory.builder().id(null).name("Womens").build();
        //When
        SubCategory savedSubCategory=subCategoryRepository.save(subCategory);
        subCategoryRepository.deleteById(savedSubCategory.getId());
            Optional<SubCategory> subCategory1 = subCategoryRepository.findById(savedSubCategory.getId());

        //Then
        then(Optional.empty());
    }

    @Test
    public void SubCategoryRepository_GetByName_ReturnObject(){
        //Given
        SubCategory subCategory = SubCategory.builder().id(null).name("Womens").build();
        //When
        SubCategory savedSubCategory=subCategoryRepository.save(subCategory);
        SubCategory getByName=subCategoryRepository.getByName(savedSubCategory.getName());
        //Then
        Assertions.assertEquals(getByName.getName(),savedSubCategory.getName());
    }

}
