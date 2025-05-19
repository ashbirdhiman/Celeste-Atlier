package com.celesteatelier.service;

import com.celesteatelier.FeignClient.OrderService;
import com.celesteatelier.model.Cart;
import com.celesteatelier.model.CartItem;
import com.celesteatelier.model.User;
import com.celesteatelier.repository.CartItemRepo;
import com.celesteatelier.repository.CartRepo;
import com.celesteatelier.repository.UserRepo;

import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CartService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private OrderService orderService;

    public Cart addToCart(Integer userId, CartItem item) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepo.findByUserUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setTotalPrice(0.00);
        }

        item.setCart(cart);
        cart.getItems().add(item);
        cart.setTotalPrice(cart.getTotalPrice() + (item.getQuantity() * 100)); // Assuming fixed product price

        cartRepo.save(cart);
        return cart;
    }

    public List<CartItem> getCartItems(Integer userId) {
        Cart cart = cartRepo.findByUserUserId(userId);
        return (cart != null) ? cart.getItems() : List.of();
    }

    public void removeFromCart(Integer userId, Integer productId) {
        Cart cart = cartRepo.findByUserUserId(userId);
        if (cart != null && cart.getItems() != null) {
            Iterator<CartItem> iterator = cart.getItems().iterator();
            while (iterator.hasNext()) {
                CartItem item = iterator.next();
                //TODO
//                if (item.getProductId().equals(productId)) {
                    iterator.remove();
                    cartItemRepo.delete(item);
//                }
            }
            cartRepo.save(cart);
        }
    }

    public void checkout(Integer userId) {
        Cart cart = cartRepo.findByUserUserId(userId);
        if (cart != null) {
            cartItemRepo.deleteAll(cart.getItems());
            cart.getItems().clear();
            cart.setTotalPrice(0.0);
            cartRepo.save(cart);
        }
        orderService.createOrder(null);
    }

}
