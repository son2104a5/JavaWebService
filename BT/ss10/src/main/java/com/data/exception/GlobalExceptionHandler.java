package com.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<?> handleBadRequest(Exception ex) {
        Map<String, Object> err = new HashMap<>();
        err.put("status", 400);
        err.put("message", "Dữ liệu gửi lên chưa đúng");
        err.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFound(Exception ex) {
        Map<String, Object> err = new HashMap<>();
        err.put("status", 404);
        err.put("message", "Không tìm thấy tài khoản");
        err.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleServerError(Exception ex) {
        Map<String, Object> err = new HashMap<>();
        err.put("status", 500);
        err.put("message", "Lỗi server");
        err.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
