package com.data.service.product;

import com.data.model.entity.ProductDetail;
import com.data.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetail> findByProductId(Long productId) {
        return productDetailRepository.findAll().stream()
                .filter(productDetail -> productDetail.getProduct().getProductId().equals(productId))
                .toList();
    }

    @Override
    public List<ProductDetail> findAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public ProductDetail findById(Long id) {
        return productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot found product detail with id: " + id));
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public boolean delete(Long id) {
        productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot found product detail with id: " + id));
        productDetailRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductDetail update(ProductDetail productDetail, Long id) {
        productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot found product detail with id: " + id));
        productDetail.getProduct().setProductId(id);
        productDetail.setProductDetailId(id);
        return productDetailRepository.save(productDetail);
    }
}
