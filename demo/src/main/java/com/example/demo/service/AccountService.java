package com.example.demo.service;

import com.example.demo.domain.model.account.Account;
import com.example.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
}
