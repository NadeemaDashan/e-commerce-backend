package org.example.service.impl;

import org.example.dto.CategoryDto;
import org.example.dto.ProductDto;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.service.CategoryService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Boolean addProduct(ProductDto productDto) {
        CategoryDto category=categoryService.getCategoryByName(productDto.getCategory().getName());
        Long id=category.getId();
        Product product = Product.builder().name(productDto.getName()).id(productDto.getId()).desc(productDto.getDesc()).price(productDto.getPrice()).soldCount(productDto.getSoldCount()).category(Category.builder().id(id).name(productDto.getCategory().getName()).build()).build();
        Product product1=productRepository.save(product);
        if (product1.getId()!=null){
            return true;
        }
        return true;
    }
}
