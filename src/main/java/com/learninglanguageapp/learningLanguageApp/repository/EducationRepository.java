package com.learninglanguageapp.learningLanguageApp.repository;

import com.learninglanguageapp.learningLanguageApp.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface EducationRepository extends JpaRepository<Education, UUID> {
    List<Education> findAllByAccountId(UUID accountId);
    @Query("SELECT e FROM Education e WHERE e.account.id = :accountId AND e.language.langName = :educationLang")
    Education findByAccountIdAndEducationLang(
            @Param("accountId") UUID accountId,
            @Param("educationLang") String educationLang
    );
}
