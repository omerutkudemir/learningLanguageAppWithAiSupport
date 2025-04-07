package com.learninglanguageapp.learningLanguageApp.controller;

import com.learninglanguageapp.learningLanguageApp.dto.AccountDto;
import com.learninglanguageapp.learningLanguageApp.dto.AccountEducationDto;
import com.learninglanguageapp.learningLanguageApp.dto.CreateAccountRequest;
import com.learninglanguageapp.learningLanguageApp.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/account")
public class AccountController {
    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest createAccountRequest)
    {
        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") UUID accountId)
    {
        return ResponseEntity.ok(accountService.getAccount(accountId));
    }


}
