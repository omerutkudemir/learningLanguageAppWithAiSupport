package com.learninglanguageapp.learningLanguageApp.repository;

import com.learninglanguageapp.learningLanguageApp.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LanguageRepository extends JpaRepository<Language , UUID> {
    Language findByLangName(String langName);
}
