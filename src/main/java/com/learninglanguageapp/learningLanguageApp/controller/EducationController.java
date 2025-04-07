package com.learninglanguageapp.learningLanguageApp.controller;

import com.learninglanguageapp.learningLanguageApp.dto.*;
import com.learninglanguageapp.learningLanguageApp.model.Account;
import com.learninglanguageapp.learningLanguageApp.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/education")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }


    @GetMapping
    public ResponseEntity<List<EducationDto>> getAllEdu()
    {
        return ResponseEntity.ok(educationService.getAllEdu());
    }

    @PostMapping
    public ResponseEntity<EducationDto> selectEdu(@RequestBody SelectEducationRequest selectEducationRequest)
    {
        return ResponseEntity.ok(educationService.selectEdu(selectEducationRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<EducationDto>> getEdu(@PathVariable("id") UUID accountId)
    {
        return ResponseEntity.ok(educationService.getEdu(accountId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EducationDto> updateEdu(@RequestBody UpdateEducationRequest updateEducationRequest)
    {
        return ResponseEntity.ok(educationService.updateEdu(updateEducationRequest));
    }
    @GetMapping("/{id}/detail")
    public  ResponseEntity<AccountEducationDto> getAccountDetail(@PathVariable("id") UUID accountId)
    {
        return ResponseEntity.ok(educationService.getAccountEdu(accountId));
    }

}
