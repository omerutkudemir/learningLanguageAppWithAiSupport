package com.learninglanguageapp.learningLanguageApp.repository;

import com.learninglanguageapp.learningLanguageApp.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

}
