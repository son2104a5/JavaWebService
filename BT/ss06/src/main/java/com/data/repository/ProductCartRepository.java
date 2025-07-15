package com.data.repository;

import com.data.entity.ProductCart;
import com.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    List<ProductCart> findByUser(User user);
}
