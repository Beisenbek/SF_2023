package com.example.demo.service;

import com.example.demo.domain.dto.AccountCreateDTO;
import com.example.demo.domain.event.AccountCreatedEvent;
import com.example.demo.domain.model.account.Account;
import com.example.demo.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    private final ApplicationEventPublisher eventPublisher;

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createAccount(AccountCreateDTO accountCreateDTO) throws JsonProcessingException {
        Account account = new Account();
        account.setEmail(accountCreateDTO.getEmail());
        account.setPassword(accountCreateDTO.getPassword());
        account.setUsername(accountCreateDTO.getUsername());
        Account savedAccount = accountRepository.save(account);

        AccountCreatedEvent event = new AccountCreatedEvent();
        event.setEmittedDate(LocalDateTime.now());
        event.setAggregateObjectType("Account");
        event.setAggregateObjectId(String.valueOf(savedAccount.getId()));

        ObjectMapper mapper = new ObjectMapper();
        event.setMessagePayload(mapper.writeValueAsString(savedAccount));

        eventPublisher.publishEvent(event);
        return savedAccount;
    }

    @KafkaListener(topicPattern = "my_super_topic")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
