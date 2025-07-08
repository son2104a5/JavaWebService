package com.data.repo;

import com.data.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void save(Product product);
    Product findById(Long id);
    void delete(Product product);
}
