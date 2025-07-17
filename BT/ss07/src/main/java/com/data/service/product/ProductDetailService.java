package com.data.service.product;

import com.data.model.entity.ProductDetail;
import com.data.service.IService;

import java.util.List;

public interface ProductDetailService extends IService<ProductDetail> {
    List<ProductDetail> findByProductId(Long productId);
}
