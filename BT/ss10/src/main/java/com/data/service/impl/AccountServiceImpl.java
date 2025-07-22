package com.data.service.impl;

import com.data.model.dto.request.AccountRequestDTO;
import com.data.model.entity.Account;
import com.data.model.entity.enums.AccountStatus;
import com.data.repo.AccountRepository;
import com.data.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> gets() {
        return accountRepository.findAll();
    }

    @Override
    public Account insert(AccountRequestDTO entity) {
        Account account = Account.builder()
                .fullname(entity.getFullname())
                .phone(entity.getPhone())
                .cccd(entity.getCccd())
                .email(entity.getEmail())
                .money(entity.getMoney())
                .status(AccountStatus.ACTIVE)
                .build();
        return accountRepository.save(account);
    }

    @Override
    public Account update(AccountRequestDTO entity, UUID id) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài khoản với ID: " + id));
        existingAccount.setFullname(entity.getFullname());
        existingAccount.setEmail(entity.getEmail());
        existingAccount.setPhone(entity.getPhone());
        existingAccount.setCccd(entity.getCccd());
        existingAccount.setMoney(entity.getMoney());
        return accountRepository.save(existingAccount);
    }

    @Override
    public void delete(UUID id) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài khoản với ID: " + id));
        existingAccount.setStatus(AccountStatus.INACTIVE);
        accountRepository.save(existingAccount);
    }

    @Override
    public Account getByCccd(String cccd) {
        return accountRepository.findByCccdContainingIgnoreCase(cccd).orElseThrow(() ->
            new NoSuchElementException("Không tìm thấy tài khoản với CCCD: " + cccd));
    }

    @Override
    public List<Account> getByPhone(String phone) {
        return accountRepository.findByPhoneContainingIgnoreCase(phone);
    }
}
