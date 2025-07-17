package com.data.model.dto.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private Long customerId;
    private Long employeeId;
    private List<OrderItemDTO> items;
}

