package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.ResourceNotFoundException;
import org.example.dto.CartDto;
import org.example.entity.Cart;
import org.example.entity.Product;
import org.example.entity.Stock;
import org.example.repository.CartRepository;
import org.example.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ObjectMapper mapper;

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public boolean addCart(CartDto cartDto) {
        cartDto.setCompleted(false);
        Cart cart = mapper.convertValue(cartDto, Cart.class);
        Cart saved = cartRepository.save(cart);
        return saved.getId() != null;
    }

//    @Override
//    public Cart updateCart(Long id, CartDto cartDto) {
//        if (!cartRepository.existsById(id)){
//            throw new ResourceNotFoundException("Cart item not found : " + id);
//        }
//        Cart existingCart = cartRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Cart item not found : " + id));
//
//        BeanUtils.copyProperties(cartDto, existingCart, "id");
//        return cartRepository.save(existingCart);
//    }

    @Override
    public boolean updateStatus(long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.setCompleted(true);
            cartRepository.save(cart);
            return true;
        }
        return false;
    }

    @Override
    public CartDto getCartById(long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        return cartOptional.map(cart -> mapper.convertValue(cart, CartDto.class)).orElse(null);
    }

    @Override
    public List<CartDto> getCartByCustomer(long id) {
        List<Cart> allByCustomerId = cartRepository.findAllByCustomerId(id);
        return convertDto(allByCustomerId);
    }

    public List<CartDto> convertDto(List<Cart> listCart) {
        List<CartDto> cartDtoList = new ArrayList<>();
        for (Cart cart : listCart) {
            CartDto cartDto = new CartDto();
            Stock stock = new Stock();
            Product product = new Product();
            stock.setSize(cart.getStock().getSize());
            stock.setColor(cart.getStock().getColor());
            stock.setId(cart.getStock().getId());
            stock.setPrice(cart.getStock().getPrice());
            product.setName(cart.getStock().getProduct().getName());
            stock.setProduct(product);
            cartDto.setStock(stock);
            cartDto.setId(cart.getId());
            cartDto.setQty(cart.getQty());
            cartDto.setProductTot((double) cart.getProductTot());
            cartDtoList.add(cartDto);
        }
        return cartDtoList;
    }

    @Override
    public List<CartDto> getAllCartDetails() {
        Iterable<Cart> cartIterable = cartRepository.findAll();
        List<CartDto> cartDtos = new ArrayList<>();
        cartIterable.forEach(cart -> cartDtos.add(mapper.convertValue(cart, CartDto.class)));
        return cartDtos;
    }
}

