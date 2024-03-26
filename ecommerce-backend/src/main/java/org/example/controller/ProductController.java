//package org.example.controller;
//
//import org.example.dto.ProductDto;
//import org.example.service.ProductService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/product")
//public class ProductController {
//
//    private final ProductService productService;
//
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @PostMapping("/add")
//    public boolean addProduct(@RequestBody ProductDto productDto){
//        return productService.addProduct(productDto);
//    }
//}
