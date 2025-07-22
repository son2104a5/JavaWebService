package com.data.service.impl;

import com.data.model.entity.Account;
import com.data.model.entity.Transaction;
import com.data.repo.AccountRepository;
import com.data.repo.TransactionRepository;
import com.data.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Override
    public Transaction insertTransaction(Transaction transaction) {
        return null;
    }
}
