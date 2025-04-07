package com.learninglanguageapp.learningLanguageApp.dto;

import com.learninglanguageapp.learningLanguageApp.model.Account;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AccountEducationDtoConverter {
    public AccountEducationDto convert(AccountDto from, List<EducationDto> educationDto)
    {
        return new AccountEducationDto(
                from.getName(),
                from.getSurName(),
                educationDto
        );
    }
}
