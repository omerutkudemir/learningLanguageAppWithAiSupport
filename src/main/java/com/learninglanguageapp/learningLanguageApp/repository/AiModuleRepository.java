package com.learninglanguageapp.learningLanguageApp.repository;

import com.learninglanguageapp.learningLanguageApp.model.AiResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AiModuleRepository extends JpaRepository<AiResponse, UUID> {
}
