package org.example.controller;

import org.example.dto.OrderDto;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public boolean addOrder(@RequestBody OrderDto orderDto){
        return orderService.addOrder(orderDto);
    }
}
