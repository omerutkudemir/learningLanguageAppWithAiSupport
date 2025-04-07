package com.learninglanguageapp.learningLanguageApp.repository;

import com.learninglanguageapp.learningLanguageApp.model.Education;
import com.learninglanguageapp.learningLanguageApp.model.UserLastTalkWithAi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserLastTalkWithAiRepository extends JpaRepository<UserLastTalkWithAi,UUID> {
    @Query("SELECT COUNT(u) > 0 FROM UserLastTalkWithAi u WHERE u.education = :education")
    boolean isAnyConversation(@Param("education") Education education);
    UserLastTalkWithAi findByEducation(Education education);

    UserLastTalkWithAi findByEducationId(UUID education);
}
