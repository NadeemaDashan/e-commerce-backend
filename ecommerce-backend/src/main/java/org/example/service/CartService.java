package org.example.service;

import org.example.dto.CartDto;
import org.example.entity.Cart;

import java.util.List;

public interface CartService {
    boolean addCart(CartDto cartDto);

    List<CartDto> getAllCartDetails();

    Cart upadateCart(Long id, CartDto cartDto);

    boolean updateStatus(long id);

    CartDto getCartById(long id);
}
