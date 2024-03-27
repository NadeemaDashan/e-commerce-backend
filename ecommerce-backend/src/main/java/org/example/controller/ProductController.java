package org.example.controller;

import org.example.dto.ProductDto;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public boolean addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @GetMapping("/get/all")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public ProductDto getProductById(@PathVariable Long id){
       return productService.getProductById(id);
    }

    @GetMapping("/get/category/{name}")
    public List<ProductDto> getProductsByCategory(@PathVariable String categoryName){
        return productService.getProductByCategory(categoryName);
    }
}
