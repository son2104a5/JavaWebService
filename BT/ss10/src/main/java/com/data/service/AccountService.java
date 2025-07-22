package com.data.service;

import com.data.model.dto.request.AccountRequestDTO;
import com.data.model.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    List<Account> gets();
    Account insert(AccountRequestDTO dto);
    Account update(AccountRequestDTO dto, UUID id);
    void delete(UUID id);
    Account getByCccd(String cccd);
    List<Account> getByPhone(String phone);
}
