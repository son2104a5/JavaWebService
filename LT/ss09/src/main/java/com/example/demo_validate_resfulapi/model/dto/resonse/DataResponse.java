package com.example.demo_validate_resfulapi.model.dto.resonse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataResponse <T>{
    private T data;
    private HttpStatus status;
}
