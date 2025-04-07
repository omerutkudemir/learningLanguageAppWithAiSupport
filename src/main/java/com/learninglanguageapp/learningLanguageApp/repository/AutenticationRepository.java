package com.learninglanguageapp.learningLanguageApp.repository;

import com.learninglanguageapp.learningLanguageApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface AutenticationRepository extends JpaRepository<Account, UUID> {


}
