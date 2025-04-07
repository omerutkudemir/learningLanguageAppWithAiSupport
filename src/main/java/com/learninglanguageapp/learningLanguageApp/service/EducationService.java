package com.learninglanguageapp.learningLanguageApp.service;

import com.learninglanguageapp.learningLanguageApp.dto.*;
import com.learninglanguageapp.learningLanguageApp.model.Account;
import com.learninglanguageapp.learningLanguageApp.model.Education;
import com.learninglanguageapp.learningLanguageApp.model.Language;
import com.learninglanguageapp.learningLanguageApp.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final EducationDtoConverter educationDtoConverter;
    private final AccountService accountService;
    private final LanguageService languageService;
    private final AccountDtoConverter accountDtoConverter;
    private final AccountEducationDtoConverter accountEducationDtoConverter;
    public EducationService(EducationRepository educationRepository, EducationDtoConverter educationDtoConverter, AccountService accountService, LanguageService languageService, AccountDtoConverter accountDtoConverter, AccountEducationDtoConverter accountEducationDtoConverter) {
        this.educationRepository = educationRepository;
        this.educationDtoConverter = educationDtoConverter;
        this.accountService = accountService;
        this.languageService = languageService;
        this.accountDtoConverter = accountDtoConverter;
        this.accountEducationDtoConverter = accountEducationDtoConverter;
    }

    public EducationDto selectEdu(SelectEducationRequest selectEducationRequest) {
        if (selectEducationRequest.getAccountId() == null) {
            throw new IllegalArgumentException("Account ID cannot be null!");
        }

        Optional<Education> education= educationRepository.findById(selectEducationRequest.getEducationId());
        Education educationEntity = education.orElse(new Education());
       Account account= accountService.saveAccount(educationEntity,selectEducationRequest.getAccountId());
        educationEntity.setAccount(account);
        return educationDtoConverter.convert(educationRepository.save(educationEntity));
    }



    public List<EducationDto> getEdu(UUID accountId) {
        List<Education> educationList = educationRepository.findAllByAccountId(accountId);
        return educationList.stream().map(educationDtoConverter::convert).collect(Collectors.toList());
    }


    public EducationDto updateEdu(UpdateEducationRequest updateEducationRequest) {
        Education education = educationRepository.findByAccountIdAndEducationLang(
                updateEducationRequest.getAccountId(),
                updateEducationRequest.getEducationLang()
        );


        education.setEducationLevel(updateEducationRequest.getLastExerciesLevel());
        return educationDtoConverter.convert(educationRepository.save(education)
);
    }
    public EducationDto updateEduForAi(Education updateEducationRequest) {

        return educationDtoConverter.convert(educationRepository.save(updateEducationRequest)
        );
    }
    public AccountEducationDto getAccountEdu(UUID accountId) {
        List<EducationDto> educations=getEdu(accountId);
        Account account = accountService.getAccountForEdu(accountId);
        AccountDto accountDto = accountDtoConverter.convert(account);
        AccountEducationDto accountEducationDto = accountEducationDtoConverter.convert(accountDto,educations);

        return accountEducationDto;
    }
    public Optional<Education> getEduForExercise(UUID educationId)
    {
        Optional<Education> education= educationRepository.findById(educationId);
        return education;
    }


    public List<EducationDto> getAllEdu() {

        return educationRepository.findAll().stream().map(educationDtoConverter::convert).collect(Collectors.toList());
    }
}
