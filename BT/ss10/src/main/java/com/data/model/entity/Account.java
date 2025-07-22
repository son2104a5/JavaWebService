package com.data.model.entity;

import com.data.model.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue
    private UUID id;
    private String fullname;
    private String email;
    private String phone;
    private String cccd;
    private Double money;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;
}
