package com.data.service;

import com.data.dto.ProductPagination;
import com.data.entity.Product;
import com.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductPagination getAllProducts(Pageable pageable, String searchName) {
        Page<Product> pageResult;
        if (searchName != null && !searchName.trim().isEmpty()) {
            pageResult = productRepository.findByNameContainingIgnoreCase(searchName, pageable);
        } else {
            pageResult = productRepository.findAll(pageable);
        }

        return ProductPagination.builder()
                .products(pageResult.getContent())
                .totalPage(pageResult.getTotalPages())
                .pageSize(pageResult.getSize())
                .currentPage(pageResult.getNumber())
                .build();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setDescription(updatedProduct.getDescription());
        existing.setStock(updatedProduct.getStock());
        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
