package com.data.service.product;

import com.data.model.entity.Product;
import com.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot found product with id: " + id));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product, Long id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot found product with id: " + id));
        product.setProductId(id);
        return productRepository.save(product);
    }

    @Override
    public boolean delete(Long id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot found product with id: " + id));
        productRepository.deleteById(id);
        return true;
    }
}
