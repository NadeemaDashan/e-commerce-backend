package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ObjectMapper objectMapper;

    private final ProductRepository productRepository;

    @Autowired
    CategoryServiceImpl categoryService;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Boolean addProduct(ProductDto productDto) {
        Product product=objectMapper.convertValue(productDto, Product.class);
        Product productSaved=productRepository.save(product);
        if (productSaved.getId()==null){
            return false;
        }
        CategoryDto category =categoryService.getCategoryByName(productDto.getCategory().getName());
        if (category.getId()!=null){
            return true;
        }
        return false;
    }
}
