package com.example.demo_validate_resfulapi.service;

import com.example.demo_validate_resfulapi.model.dto.request.AccountRequestDTO;
import com.example.demo_validate_resfulapi.model.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account>  getAccounts();
    Account getAccount(Integer accountId);
    Account insertAccount(AccountRequestDTO accountRequestDTO);
    Account updateAccount(Account account, Integer accountId);
    void deleteAccount(Integer accountId);
}
