package com.learninglanguageapp.learningLanguageApp.dto;

import com.learninglanguageapp.learningLanguageApp.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

    public AccountDto convert(Account from) {
        return new AccountDto(
                from.getId(),
                from.getName(),
                from.getSurName(),
                from.getEmail(),
                from.getPassword()
        );
    }
}
