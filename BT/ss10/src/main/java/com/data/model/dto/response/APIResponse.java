package com.data.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
    private String message;
    private T data;
    private Boolean success;
    private int statusCode;
}
