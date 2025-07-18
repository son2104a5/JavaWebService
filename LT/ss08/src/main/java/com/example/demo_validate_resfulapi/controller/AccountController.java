package com.example.demo_validate_resfulapi.controller;

import com.example.demo_validate_resfulapi.model.dto.request.AccountRequestDTO;
import com.example.demo_validate_resfulapi.model.dto.resonse.DataResponse;
import com.example.demo_validate_resfulapi.model.entity.Account;
import com.example.demo_validate_resfulapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<DataResponse<Account>> postAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO){
        return new ResponseEntity<>(new DataResponse<Account>(accountService.insertAccount(accountRequestDTO), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<DataResponse<Account>> getAccount(@PathVariable Integer accountId){
        return new ResponseEntity<>(new DataResponse<>(accountService.getAccount(accountId), HttpStatus.OK), HttpStatus.OK);
    }
}
