package com.data.controller;

import com.data.model.dto.request.AccountRequestDTO;
import com.data.model.dto.response.APIResponse;
import com.data.model.entity.Account;
import com.data.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // GET all accounts
    @GetMapping
    public ResponseEntity<APIResponse<List<Account>>> getAccounts() {
        return ResponseEntity.ok(new APIResponse<>(
                "Lấy danh sách tài khoản thành công",
                accountService.gets(),
                true,
                200
        ));
    }

    // POST - Create new account
    @PostMapping
    public ResponseEntity<APIResponse<Account>> createAccount(@Valid @RequestBody AccountRequestDTO account) {
        Account created = accountService.insert(account);
        return ResponseEntity.status(201).body(new APIResponse<>(
                "Tạo tài khoản thành công",
                created,
                true,
                201
        ));
    }

    // PUT - Update existing account
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Account>> updateAccount(@PathVariable UUID id,
                                                              @Valid @RequestBody AccountRequestDTO account) {
        Account updated = accountService.update(account, id);
        return ResponseEntity.ok(new APIResponse<>(
                "Cập nhật tài khoản thành công",
                updated,
                true,
                200
        ));
    }

    // DELETE - Set status INACTIVE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET by CCCD
    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<APIResponse<Account>> getAccountByCccd(@PathVariable String cccd) {
        Account account = accountService.getByCccd(cccd);
        return ResponseEntity.ok(new APIResponse<>(
                "Tìm tài khoản theo CCCD thành công",
                account,
                true,
                200
        ));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<APIResponse<List<Account>>> getAccountByPhone(@PathVariable String phone) {
        List<Account> account = accountService.getByPhone(phone);
        if (account.isEmpty()) {
            return ResponseEntity.status(404).body(new APIResponse<>(
                    "Không tìm thấy tài khoản với số điện thoại: " + phone,
                    null,
                    false,
                    404
            ));
        }
        return ResponseEntity.ok(new APIResponse<>(
                "Tìm tài khoản theo số điện thoại thành công",
                account,
                true,
                200
        ));
    }
}
