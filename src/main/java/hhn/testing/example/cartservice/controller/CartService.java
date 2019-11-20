package hhn.testing.example.cartservice.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hhn.testing.example.cartservice.model.Cart;
import hhn.testing.example.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public List<Cart> getAllCarts() {
        List<Cart> carts = new ArrayList<>();
        cartRepository.findAll().forEach(cart -> carts.add(cart));
        return carts;
    }

    public Cart recalculateCart(String id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()) {
            Cart cartFromDB = cart.get();
            cartFromDB.setValue(cartFromDB.getValue().add(BigDecimal.TEN));
            return cartRepository.save(cartFromDB);
        }
        return new Cart();
    }

}