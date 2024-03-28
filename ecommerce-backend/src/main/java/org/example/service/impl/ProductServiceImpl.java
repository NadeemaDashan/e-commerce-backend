package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.service.CategoryService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    ObjectMapper objectMapper;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Boolean addProduct(ProductDto productDto) {
        if (productDto.getCategory().getName()==null){
            return false;
        }
        CategoryDto category=categoryService.getCategoryByName(productDto.getCategory().getName());
        Long id=category.getId();
        Product product = Product.builder().
                name(productDto.getName())
                .id(productDto.getId())
                .desc(productDto.getDesc())
                .price(productDto.getPrice())
                .soldCount(productDto.getSoldCount())
                .category(Category.builder().id(id).name(productDto.getCategory().getName()).build()).build();
        Product product1=productRepository.save(product);
        if (product1.getId()!=null){
            return true;
        }
        return true;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        Iterable<Product> iterableProducts=productRepository.findAll();
        Iterator<Product> iteratorProducts =iterableProducts.iterator();
        List<ProductDto> list = new ArrayList<>();
        while (iteratorProducts.hasNext()){
            Product product=iteratorProducts.next();
            ProductDto productDto=objectMapper.convertValue(product,ProductDto.class);
            productDto.setCategory
                    (Category.builder()
                            .id(product.getCategory().getId())
                            .name(product.getCategory().getName()).build());
            list.add(productDto);
        }
        return list;
    }

    @Override
    public ProductDto getProductById(long id){
        Product product=productRepository.findById(id).get();
        if (product.getId()!=null){
            ProductDto productDto=objectMapper.convertValue(product,ProductDto.class);
            productDto.
                    setCategory(Category.builder()
                            .id(product.getCategory().getId())
                            .name(product.getCategory().getName()).build());
            return productDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getProductByCategory(String categoryName) {
        List<ProductDto> listOfALlProducts=getAllProducts();
        List<ProductDto> listOfSpecificProducts = new ArrayList<>();
        for (ProductDto productDto:listOfALlProducts){
            if (productDto.getCategory().getName()==categoryName){
                listOfSpecificProducts.add(productDto);
            }
        }
        return listOfSpecificProducts;
    }
}
