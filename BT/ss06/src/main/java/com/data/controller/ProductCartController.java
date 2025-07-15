package com.data.controller;

import com.data.entity.ProductCart;
import com.data.entity.User;
import com.data.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ProductCartController {

    @Autowired
    private ProductCartService cartService;

    @GetMapping
    public List<ProductCart> getCartItems(@RequestBody User user) {
        return cartService.getCartItemsByUser(user);
    }

    @PostMapping
    public ProductCart addToCart(@RequestBody ProductCart productCart) {
        return cartService.addToCart(productCart);
    }

    @PutMapping("/{id}")
    public ProductCart updateQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        return cartService.updateQuantity(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
    }
}
