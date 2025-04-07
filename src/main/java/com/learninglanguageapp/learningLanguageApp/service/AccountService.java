package com.learninglanguageapp.learningLanguageApp.service;

import com.learninglanguageapp.learningLanguageApp.dto.*;
import com.learninglanguageapp.learningLanguageApp.model.Account;
import com.learninglanguageapp.learningLanguageApp.model.Education;
import com.learninglanguageapp.learningLanguageApp.repository.AutenticationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountDtoConverter accountDtoConverter;
    private final AutenticationRepository autenticationRepository;
    public AccountService(AccountDtoConverter accountDtoConverter, AutenticationRepository autenticationRepository) {
        this.accountDtoConverter = accountDtoConverter;
        this.autenticationRepository = autenticationRepository;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {

        if(createAccountRequest.getName()!=null&& !createAccountRequest.getName().isEmpty()&&
        createAccountRequest.getSurName()!=null&&!createAccountRequest.getSurName().isEmpty()&&
        createAccountRequest.getEmail()!=null&&!createAccountRequest.getEmail().isEmpty()&&
        createAccountRequest.getPassword()!=null&&!createAccountRequest.getPassword().isEmpty()
        )
        {
            String name = createAccountRequest.getName();
            String surName = createAccountRequest.getSurName();
            String mail=createAccountRequest.getEmail();
            String password = createAccountRequest.getPassword();
            Account account= new Account(name,surName,mail,password);

            return accountDtoConverter.convert(autenticationRepository.save(account));
        }
        return new AccountDto(null,null,null,null,null);
    }
    public AccountDto getAccount(UUID accountId)
    {
        Account account = autenticationRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Kullanıcı kaydı bulunamadı!"));

        AccountDto accountDto = accountDtoConverter.convert(account);

        return accountDto;
    }
    public Account getAccountForEdu(UUID id)
    {
        Account account = autenticationRepository.findById(id).orElseThrow(() -> new RuntimeException("Kullanıcı kaydı bulunamadı!"));
        return account;
    }

    public Account saveAccount(Education education, UUID accountId) {
        Account account = autenticationRepository.findById(accountId).orElseThrow();

        // Education'ı Account'un educations listesine ekle
        if (account.getEducations() == null) {
            account.setEducations(new ArrayList<>());
        }
        account.getEducations().add(education);

        // Account'u kaydet
        return autenticationRepository.save(account);
    }




}
