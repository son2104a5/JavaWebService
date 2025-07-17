package com.data.controller;

import com.data.model.dto.response.DataResponse;
import com.data.model.entity.ProductDetail;
import com.data.service.product.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{productId}/product-detail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<DataResponse<List<ProductDetail>>> getProductDetailsByProductId(@PathVariable String productId) {
        return new ResponseEntity<>(new DataResponse<>(productDetailService.findByProductId(Long.valueOf(productId)), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ProductDetail>> getProductDetailById(@PathVariable Long id, @PathVariable String productId) {
        return new ResponseEntity<>(new DataResponse<>(productDetailService.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<ProductDetail>> insertProductDetail(@RequestBody ProductDetail productDetail, @PathVariable String productId) {
        return new ResponseEntity<>(new DataResponse<>(productDetailService.save(productDetail), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DataResponse<ProductDetail>> updateProductDetail(@RequestBody ProductDetail productDetail, @RequestParam Long id, @PathVariable String productId) {
        return new ResponseEntity<>(new DataResponse<>(productDetailService.update(productDetail, id), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<DataResponse<Boolean>> deleteProductDetail(@RequestParam Long id, @PathVariable String productId) {
        return new ResponseEntity<>(new DataResponse<>(productDetailService.delete(id), HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }
}
