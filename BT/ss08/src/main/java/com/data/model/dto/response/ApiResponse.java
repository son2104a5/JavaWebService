package com.data.model.dto.response;

public class ApiResponse<T> {
    private Boolean status;
    private String message;
    private T data;
}
