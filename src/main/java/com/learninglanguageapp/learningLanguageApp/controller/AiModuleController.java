package com.learninglanguageapp.learningLanguageApp.controller;

import com.learninglanguageapp.learningLanguageApp.dto.*;
import com.learninglanguageapp.learningLanguageApp.service.AiModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("v1/teach_ai")
public class AiModuleController {

    private final AiModuleService aiModuleService;


    public AiModuleController(AiModuleService aiModuleService) {
        this.aiModuleService = aiModuleService;
    }
/*
    @PostMapping()
    public ResponseEntity<List<ExerciseAnalysisReport>> determinateLevelOfUser(@RequestBody ExerciseAnalysisRequest exerciseAnalysisRequest) {
        return ResponseEntity.ok(aiModuleService.doAnalysis(exerciseAnalysisRequest));
    }
*/
    @PostMapping("/talk")
    public ResponseEntity<AiResponseDto> talkToAi(@RequestBody UserTalkRequest userTalkRequest)
    {
        return ResponseEntity.ok(aiModuleService.talkToAi(userTalkRequest));
    }



@PostMapping("/translate") // Endpoint URL'si
public ResponseEntity<String> translateText(@RequestBody Map<String, String> request) {
    // Request body'den "word" anahtarını al
    String word = request.get("word");

    // Eğer "word" null veya boşsa hata döndür
    if (word == null || word.isEmpty()) {
        return ResponseEntity.badRequest().body("Word is required");
    }

    // AiModuleService üzerinden çeviri yap
    String translatedText = aiModuleService.translateWords(word);

    // Sonucu döndür
    return ResponseEntity.ok(translatedText);
}
    /*
    @GetMapping("{id}")
    public ResponseEntity<suggestedExercises> suggestExercise(@PathVariable("id")UUID educationId)
    {
        return ResponseEntity.ok(aiModuleService.suggestExercise(educationId));
    }
 */
}
