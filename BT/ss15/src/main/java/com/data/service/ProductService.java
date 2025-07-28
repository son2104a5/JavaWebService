package com.data.service;

import com.data.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
