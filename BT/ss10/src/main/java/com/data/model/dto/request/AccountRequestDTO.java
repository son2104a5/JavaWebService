package com.data.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class AccountRequestDTO {
    @NotBlank(message = "Họ tên không được để trống")
    private String fullname;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "\\d{10,11}", message = "Số điện thoại không hợp lệ")
    private String phone;

    @NotBlank(message = "CCCD không được để trống")
    @Pattern(regexp = "\\d{12}", message = "CCCD phải đủ 12 chữ số")
    private String cccd;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotNull(message = "Số dư không được để trống")
    @Min(value = 0, message = "Số dư không được âm")
    private Double money;
}
