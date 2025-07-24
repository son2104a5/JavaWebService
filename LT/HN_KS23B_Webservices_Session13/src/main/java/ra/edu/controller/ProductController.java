package ra.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.response.APIResponse;
import ra.edu.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getProducts(){
        //....
        List<Product> list = new ArrayList<>();
        list.add(new Product("p01","Tivi"));
        list.add(new Product("p02","Dien thoai"));
        list.add(new Product("p03","May tinh bang"));
        list.add(new Product("p04","Xe may"));
        list.add(new Product("p05","O to"));
        list.add(new Product("p06","Xe dap"));
        return new ResponseEntity<>(new APIResponse<>(true,"Get products successfully!",list, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Product>> postProduct(@RequestBody Product product){
        //....
        return new ResponseEntity<>(new APIResponse<>(true,"Post product successfully!",product, HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
