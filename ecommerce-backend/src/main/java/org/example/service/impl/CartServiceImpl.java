package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.ResourceNotFoundException;
import org.example.dto.CartDto;
import org.example.entity.Cart;
import org.example.entity.Stock;
import org.example.repository.CartRepository;
import org.example.service.CartService;
import org.example.service.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    StockService stockService;

    final CartRepository cartRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public boolean addCart(CartDto cartDto) {
        cartDto.setCompleted(false);
        Cart cart=mapper.convertValue(cartDto, Cart.class);
        cart.setStock(Stock.builder().id(cartDto.getStock().getId()).build());
        Cart saved = cartRepository.save(cart);
        return saved.getId() != null;
    }
    @Override
    public Cart upadateCart(Long id, CartDto cartDto) {
        cartDto.setCompleted(false);
        if (!cartRepository.existsById(id)){
            throw new ResourceNotFoundException("Cart item not found : "+id);
        }
        Cart existingCart =cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found with this id: " + id));

        BeanUtils.copyProperties(cartDto,existingCart,"id");
        return cartRepository.save(existingCart);
    }

    @Override
    public boolean updateStatus(long id) {
        Optional<Cart> cart=cartRepository.findById(id);
        if (cart.isPresent()){
            Cart cartGot=cart.get();
            cartRepository.deleteById(id);
            cartGot.setCompleted(true);
            cartRepository.save(cartGot);
            return true;
        }
        return false;
    }

    @Override
    public CartDto getCartById(long id) {
        Optional<Cart> cart=cartRepository.findById(id);
        if (cart.isPresent()){
            Cart cartGot=cart.get();
            CartDto cartDto=mapper.convertValue(cartGot,CartDto.class);
            cartDto.setStock(Stock.builder().id(cartGot.getId()).build());
            cartDto.setStock(cartGot.getStock());
            return cartDto;
        }
        return null;
    }


    @Override
    public List<CartDto> getAllCartDetails() {
        Iterable<Cart> cartIterable = cartRepository.findAll();
        Iterator<Cart> cartIterator = cartIterable.iterator();
        List<CartDto> cartDtos = new ArrayList<>();
        while(cartIterator.hasNext()){
            Cart cart = cartIterator.next();
            CartDto cartDto=mapper.convertValue(cart , CartDto.class);
            cartDtos.add(cartDto);
        }
        return cartDtos;
    }
}
