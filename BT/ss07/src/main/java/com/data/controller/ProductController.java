package com.data.controller;

import com.data.model.dto.response.DataResponse;
import com.data.model.entity.Product;
import com.data.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getProducts() {
        return new ResponseEntity<>(new DataResponse<>(productService.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(new DataResponse<>(productService.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Product>> insertProduct(Product product) {
        return new ResponseEntity<>(new DataResponse<>(productService.save(product), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DataResponse<Product>> updateProduct(@RequestBody Product product, @RequestParam Long id) {
        return new ResponseEntity<>(new DataResponse<>(productService.update(product, id), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<DataResponse<Boolean>> deleteProduct(@RequestParam Long id) {
        return new ResponseEntity<>(new DataResponse<>(productService.delete(id), HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }
}
