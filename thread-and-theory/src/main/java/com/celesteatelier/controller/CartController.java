package com.celesteatelier.controller;

import com.celesteatelier.model.Cart;
import com.celesteatelier.model.CartItem;
import com.celesteatelier.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private  CartService cartService;

    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addToCart(@PathVariable Integer userId, @RequestBody CartItem item) {
        return ResponseEntity.ok(cartService.addToCart(userId, item));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Integer userId, @PathVariable Integer productId) {
        cartService.removeFromCart(userId, productId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<String> checkout(@PathVariable Integer userId) {
        cartService.checkout(userId);
        return ResponseEntity.ok("Checkout successful, cart cleared.");
    }
}
