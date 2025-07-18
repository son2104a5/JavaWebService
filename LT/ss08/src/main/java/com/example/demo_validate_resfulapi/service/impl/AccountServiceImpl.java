package com.example.demo_validate_resfulapi.service.impl;

import com.example.demo_validate_resfulapi.model.dto.request.AccountRequestDTO;
import com.example.demo_validate_resfulapi.model.entity.Account;
import com.example.demo_validate_resfulapi.repository.AccountRepository;
import com.example.demo_validate_resfulapi.service.AccountService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Integer accountId) {
        return accountRepository.findById(accountId).orElseThrow(()-> new NoSuchElementException("Khong ton tai account co id: "+accountId));
    }

    @Override
    public Account insertAccount(AccountRequestDTO accountRequestDTO) {
        Account acc = Account.builder()
                .username(accountRequestDTO.getUsername())
                .password(accountRequestDTO.getPassword())
                .fullName(accountRequestDTO.getFullName())
                .email(accountRequestDTO.getEmail())
                .phone(accountRequestDTO.getPhone())
                .gender(accountRequestDTO.getGender())
                .build();
        return accountRepository.save(acc);
    }

    @Override
    public Account updateAccount(Account account, Integer accountId) {
        accountRepository.findById(accountId).orElseThrow(()-> new NoSuchElementException("Khong ton tai account co id: "+accountId));
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountRepository.findById(accountId).orElseThrow(()-> new NoSuchElementException("Khong ton tai account co id: "+accountId));
        accountRepository.deleteById(accountId);
    }
}
