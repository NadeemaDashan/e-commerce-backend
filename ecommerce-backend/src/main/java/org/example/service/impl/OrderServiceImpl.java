package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.OrderDto;
import org.example.entity.Orders;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean addOrder(OrderDto orderDto) {
        Orders orders=objectMapper.convertValue(orderDto, Orders.class);
        Orders savedOrders=orderRepository.save(orders);
        return savedOrders.getId() != null;
    }
}
