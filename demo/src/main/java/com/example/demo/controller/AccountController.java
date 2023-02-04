package com.example.demo.controller;

import com.example.demo.domain.model.Greeting;
import com.example.demo.domain.model.account.Account;
import com.example.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class AccountController {
    private final AccountService accountService;
    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountService.findAllAccounts();
    }
}
