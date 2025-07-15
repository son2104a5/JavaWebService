package com.data.service;

import com.data.entity.ProductCart;
import com.data.entity.User;
import com.data.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartService {
    @Autowired
    private ProductCartRepository cartRepository;

    public List<ProductCart> getCartItemsByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public ProductCart addToCart(ProductCart productCart) {
        return cartRepository.save(productCart);
    }

    public ProductCart updateQuantity(Long id, Integer quantity) {
        ProductCart cartItem = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        return cartRepository.save(cartItem);
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}
