package com.data.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý lỗi @Valid không hợp lệ
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(createErrorResponse("Dữ liệu không hợp lệ", errors), HttpStatus.BAD_REQUEST);
    }

    // Xử lý vi phạm ràng buộc (javax.validation)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String path = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(path, message);
        });

        return new ResponseEntity<>(createErrorResponse("Vi phạm ràng buộc", errors), HttpStatus.BAD_REQUEST);
    }

    // Xử lý không tìm thấy phần tử
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElement(NoSuchElementException ex) {
        return new ResponseEntity<>(createErrorResponse("Không tìm thấy phần tử", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Xử lý lỗi ngày giờ không đúng định dạng
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException ex) {
        return new ResponseEntity<>(createErrorResponse("Định dạng ngày giờ không hợp lệ", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // Xử lý lỗi không tìm thấy tài nguyên (tùy chỉnh)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFound(NoResourceFoundException ex) {
        return new ResponseEntity<>(createErrorResponse("Không tìm thấy tài nguyên", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Xử lý yêu cầu không hợp lệ (tùy chỉnh)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(createErrorResponse("Yêu cầu không hợp lệ", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    // Helper tạo body JSON lỗi
    private Map<String, Object> createErrorResponse(String message, Object detail) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", message);
        error.put("detail", detail);
        return error;
    }
}
