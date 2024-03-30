package org.example.controller;

import org.example.dto.CartDto;
import org.example.entity.Cart;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/add")
    public boolean addCart(@RequestBody CartDto cartDto){
        return cartService.addCart(cartDto);
    }

    @GetMapping("/getAll")
    public List<CartDto> getAllCartDetails(){
        return cartService.getAllCartDetails();
    }

    @PutMapping("/update/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody CartDto cartDto) {
        return cartService.upadateCart(id,cartDto);
    }

    @PutMapping("/delete/{id}")
    public Boolean updateStatus(@PathVariable long id){
        return cartService.updateStatus(id);
    }

    @GetMapping("/get/{id}")
    public CartDto getCartById(@PathVariable long id){
        return cartService.getCartById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> error(MethodArgumentNotValidException exception){
        Map<String, String> map=new HashMap<>();
        List<ObjectError> list=exception.getBindingResult().getAllErrors();
        for(ObjectError item:list) {
            FieldError fieldError=(FieldError) item;
            String fieldName= fieldError.getField();
            String message = item.getDefaultMessage();
            map.put(fieldName,message);
        }
        return map;
    }
}
