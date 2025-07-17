package com.data.model.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    private String name;
    private Integer stock;
    private LocalDate expiry;
    private MultipartFile image;
}
