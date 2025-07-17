package com.data.model.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {
    private String name;
    private String description;
    private Double price;
    private String status;
    private MultipartFile image;
}
