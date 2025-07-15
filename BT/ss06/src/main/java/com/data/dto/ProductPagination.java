package com.data.dto;

import com.data.entity.Product;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPagination {
    private List<Product> products;
    private int totalPage;
    private int pageSize;
    private int currentPage;
}
